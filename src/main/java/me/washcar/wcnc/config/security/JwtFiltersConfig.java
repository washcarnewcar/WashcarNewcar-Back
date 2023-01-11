package me.washcar.wcnc.config.security;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.filter.CustomAuthenticationFilter;
import me.washcar.wcnc.filter.CustomAuthorizationFilter;
import me.washcar.wcnc.util.JwtManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtFiltersConfig extends AbstractHttpConfigurer<JwtFiltersConfig, HttpSecurity> {
    // 무슨 코드인지 모를 경우 "Spring Security Custom DSL" 검색!
    private final JwtManager jwtManager;
    private final CookieManager cookieManager;

    @Override
    public void configure(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new CustomAuthenticationFilter(authenticationManager, jwtManager, cookieManager));
        http.addFilterBefore(new CustomAuthorizationFilter(jwtManager), UsernamePasswordAuthenticationFilter.class);
    }
}
