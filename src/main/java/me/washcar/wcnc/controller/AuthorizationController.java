package me.washcar.wcnc.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.config.security.CookieManager;
import me.washcar.wcnc.dto._StatusCodeDto;
import me.washcar.wcnc.entity.Role;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.form.RoleToUserForm;
import me.washcar.wcnc.form.SignupCheckEmailForm;
import me.washcar.wcnc.form.SignupCheckNumberForm;
import me.washcar.wcnc.form.SignupForm;
import me.washcar.wcnc.service.AuthorizationService;
import me.washcar.wcnc.service._UserService;
import me.washcar.wcnc.util.JwtManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    private final JwtManager jwtManager;
    private final CookieManager cookieManager;

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

        // ?????? ?????????
        String refresh_token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh_token")) {
                    refresh_token = cookie.getValue();
                }
            }
        }

        if (refresh_token != null) {
            try {
                JWTVerifier verifier = JWT.require(jwtManager.getAlgorithm()).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);

                String email = decodedJWT.getSubject();
                User user = userService.getUser(email);
                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(jwtManager.getAlgorithm());

                response.addCookie(cookieManager.makeAccessTokenCookie(access_token));
                response.addCookie(cookieManager.makeRefreshTokenCookie(refresh_token));

            } catch (JWTVerificationException exception) {
                // TODO: ????????? ????????? ????????? ??????
                // refresh_token ?????? ??????
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            } catch (IllegalArgumentException | JWTCreationException exception) {
                // TODO: ????????? ????????? ????????? ??????
                // access_token ?????? ??????
                response.setHeader("error", exception.getMessage());
                response.setStatus(INTERNAL_SERVER_ERROR.value());

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
    public void logout(HttpServletResponse response) {
        response.addCookie(cookieManager.deleteAccessTokenCookie());
        response.addCookie(cookieManager.deleteRefreshTokenCookie());
    }

    @PostMapping("/signup/check/email")
    public _StatusCodeDto signupCheckEmail(@Valid @RequestBody SignupCheckEmailForm form) {
        return authorizationService.signupCheckEmail(form.getEmail());
    }

    @PostMapping("/signup/check/number")
    public _StatusCodeDto signupCheckNumber(@Valid @RequestBody SignupCheckNumberForm form) {
        return authorizationService.signupCheckNumber(form.getEmail(), form.getNumber());
    }

    @PostMapping("/signup")
    public _StatusCodeDto signup(@Valid @RequestBody SignupForm form) {
        return authorizationService.signup(form.getEmail(), form.getNumber(), form.getPassword());
    }

    @GetMapping("/user")
    public void checkUser() {
        return;
    }

}
