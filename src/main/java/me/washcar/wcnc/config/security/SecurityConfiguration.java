package me.washcar.wcnc.config.security;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.filter.CustomAuthenticationFilter;
import me.washcar.wcnc.filter.CustomAuthorizationFilter;
import me.washcar.wcnc.service.OAuth.CustomOAuth2UserService;
import me.washcar.wcnc.util.JwtManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtManager jwtManager;
    private final CookieManager cookieManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

        http.logout().disable();

        http.authorizeRequests().antMatchers("/login", "/refresh/token", "/status/**").permitAll();
        http.authorizeRequests().antMatchers("/super/**").hasAnyAuthority("ROLE_SUPER_ADMIN");
        http.authorizeRequests().antMatchers("/provider/check-slug/*").permitAll();
        http.authorizeRequests().antMatchers("/provider/**").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers("/user").authenticated();
        http.authorizeRequests().anyRequest().permitAll();

        // Spring Bean이 아닌 new로 객체 생성한 이유 :
        // CustomAuthenticationFilter는 AuthenticationManager를 멤버 변수로 사용하고 있는데,
        // SecurityConfig에 CustomAuthenticationFilter를 주입하기 위해 Bean으로 생성할 때
        // SecurityConfig에 있는 AuthenticationManager가 필요로 하기 때문에 순환참조가 발생하게 된다.
        // 따라서 new를 통해 하나의 인스턴스를 생성하게 해주어 SecurityConfig만 Bean을 생성할 수 있도록 해준다.
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(), jwtManager, cookieManager));
        // 얘는 Bean으로 해도 되는데 그냥 깔맞춤하기 위해 인스턴스화 시켰다.
        http.addFilterBefore(new CustomAuthorizationFilter(jwtManager), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}