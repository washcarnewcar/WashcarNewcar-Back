package me.washcar.wcnc.config.security;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.service.OAuth.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final JwtFiltersConfig jwtFiltersConfig;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);

        http.logout().disable();

        http.authorizeRequests().antMatchers("/login", "/refresh/token", "/status/**").permitAll();
        http.authorizeRequests().antMatchers("/super/**").hasAnyAuthority("ROLE_SUPER_ADMIN");
        http.authorizeRequests().antMatchers("/provider/check-slug/*").permitAll();
        http.authorizeRequests().antMatchers("/provider/**").hasAnyAuthority("ROLE_MANAGER");
        http.authorizeRequests().antMatchers("/user").authenticated();
        http.authorizeRequests().anyRequest().permitAll();

        http.apply(jwtFiltersConfig);

        return http.build();
    }
}