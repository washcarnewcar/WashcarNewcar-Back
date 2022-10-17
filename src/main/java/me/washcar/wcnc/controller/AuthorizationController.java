package me.washcar.wcnc.controller;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.Authorization;
import me.washcar.wcnc.service.AuthorizationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    public Authorization.loginDto login(){
        return authorizationService.login();
    }

    @GetMapping("/signup/check")
    public Authorization.signupIdCheckDto signupIdCheck(@RequestParam("id") String id) {
        return authorizationService.signupIdCheck(id);
    }

    @PostMapping("/signup")
    public Authorization.signupDto signup(){
        return authorizationService.signup();
    }

}
