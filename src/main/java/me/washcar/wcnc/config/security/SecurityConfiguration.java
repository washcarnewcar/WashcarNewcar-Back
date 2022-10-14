package me.washcar.wcnc.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 세션 비활성화
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // formLogin 비활성화
        http.formLogin().disable();

        // 기존 인증 방식 비활성화
        http.httpBasic().disable();

        // Cross site Request forgery 비활성화
        http.csrf().disable();

//        // OAuth2의 절차가 끝난 후 데이터를 처리할 서비스를 지정해준다.
//        // CustomOAuth2UserService는 OAuth2UserService를 상속받은 구현체.
//        // userService를 지정해주지 않으면 DefaultOAuth2UserService가 실행된다.
//        http.oauth2Login()
//                .successHandler(oAuth2AuthenticationSuccessHandler)
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService);
//
//        // UsernamePasswordAuthenticationFilter 전에(앞에) JWT 필터 적용
//        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//
//        // 예외처리
//        http.exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler);

        // 경로 보안 설정
        http.authorizeRequests(authorize -> authorize
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().permitAll()
        );

        return http.build();
    }
}