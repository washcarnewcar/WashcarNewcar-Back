package me.washcar.wcnc.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String userEmail = request.getParameter("email");
        String password = request.getParameter("password");

        log.info("userEmail = {}", userEmail);
        log.info("password = {}", password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEmail, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        User user = (User)authentication.getPrincipal();
        List<String> roleClaimList = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 180 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", roleClaimList)
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 360 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        Cookie accessToken = new Cookie("access_token", access_token);
        accessToken.setSecure(true);
        accessToken.setHttpOnly(true);
        accessToken.setPath("/");
        accessToken.setDomain("washcar.me");
        accessToken.setMaxAge(60 * 60 * 24 * 90);
        response.addCookie(accessToken);

        Cookie refreshToken = new Cookie("refresh_token", refresh_token);
        refreshToken.setSecure(true);
        refreshToken.setHttpOnly(true);
        refreshToken.setPath("/");
        refreshToken.setDomain("washcar.me");
        accessToken.setMaxAge(60 * 60 * 24 * 90);
        response.addCookie(refreshToken);
    }

}
