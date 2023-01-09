package me.washcar.wcnc.config.security;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.filter.CustomAuthenticationFilter;
import me.washcar.wcnc.filter.CustomAuthorizationFilter;
import me.washcar.wcnc.service.OAuth.CustomOAuth2UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfiguration {

    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.oauth2Login()
            .successHandler(oAuth2AuthenticationSuccessHandler)
            .userInfoEndpoint()
            .userService(customOAuth2UserService);

        http.logout().disable();

        http.authorizeRequests(auth -> {
            auth.antMatchers("/login", "/refresh/token", "/status/**").permitAll();
            auth.antMatchers("/super/**").hasAnyAuthority("ROLE_SUPER_ADMIN");
            auth.antMatchers("/provider/check-slug/*").permitAll();
            auth.antMatchers("/provider/**").hasAnyAuthority("ROLE_USER");
            auth.antMatchers("/user").authenticated();
            auth.anyRequest().permitAll();
        });

        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new CustomAuthenticationFilter(authenticationManager));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
