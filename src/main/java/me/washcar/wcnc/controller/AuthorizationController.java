package me.washcar.wcnc.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.Authorization;
import me.washcar.wcnc.entity.Role;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.form.RoleToUserForm;
import me.washcar.wcnc.form.SignUpForm;
import me.washcar.wcnc.service.AuthorizationService;
import me.washcar.wcnc.service._UserService;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;
    private final _UserService userService;

    @GetMapping("/super/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/super/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/super/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/super/role/addtouser")
    public ResponseEntity<Role> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUserId(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/refresh/token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 쿠키 가져옴
        String refresh_token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh_token")) {
                    refresh_token = cookie.getValue();
                }
            }
        }

        // String authorizationHeader = request.getHeader(AUTHORIZATION);
        // if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        if (refresh_token != null) {
            try {
                // String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);

                String email = decodedJWT.getSubject();
                User user = userService.getUser(email);
                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                ResponseCookie accessToken = ResponseCookie.from("access_token", access_token)
                        .secure(true)
                        .httpOnly(true)
                        .path("/")
                        // .sameSite("None")
                        .domain("washcar.me")
                        .build();
                response.addHeader("Set-Cookie", accessToken.toString());
                // Cookie accessToken = new Cookie("access_token", access_token);
                // accessToken.setSecure(true);
                // accessToken.setHttpOnly(true);
                // accessToken.setPath("/");
                // response.addCookie(accessToken);

                ResponseCookie refreshToken = ResponseCookie.from("refresh_token", refresh_token)
                        .secure(true)
                        .httpOnly(true)
                        .path("/")
                        // .sameSite("None")
                        .domain("washcar.me")
                        .build();
                response.addHeader("Set-Cookie", refreshToken.toString());
                // Cookie refreshToken = new Cookie("refresh_token", refresh_token);
                // refreshToken.setSecure(true);
                // refreshToken.setHttpOnly(true);
                // refreshToken.setPath("/");
                // response.addCookie(refreshToken);

                // Map<String, String> tokens = new HashMap<>();
                // tokens.put("access_token", access_token);
                // tokens.put("refresh_token", refresh_token);
                // response.setContentType(APPLICATION_JSON_VALUE);
                // new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                // } catch (Exception exception) {
            } catch (JWTVerificationException exception) {
                // TODO: 추후에 커스텀 예외로 전환
                // refresh_token 검증 실패
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                // response.sendError(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            } catch (IllegalArgumentException | JWTCreationException exception) {
                // TODO: 추후에 커스텀 예외로 전환
                // access_token 생성 실패
                response.setHeader("error", exception.getMessage());
                response.setStatus(INTERNAL_SERVER_ERROR.value());
                // response.sendError(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response){
        Cookie accessToken = new Cookie("access_token", null);
        accessToken.setMaxAge(0);
        accessToken.setPath("/");
        response.addCookie(accessToken);

        Cookie refreshToken = new Cookie("refresh_token", null);
        refreshToken.setMaxAge(0);
        refreshToken.setPath("/");
        response.addCookie(refreshToken);
    }

    @PostMapping("/signup")
    public Authorization.signupDto signup(@RequestBody SignUpForm form) {
        return authorizationService.signup(form.getId(), form.getPassword(), form.getMobile_carrier(), form.getPhone());
    }

    @GetMapping("/user")
    public void checkUser() {
        return;
    }

}
