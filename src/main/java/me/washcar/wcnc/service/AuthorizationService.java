package me.washcar.wcnc.service;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.Authorization;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorizationService {
    //TODO 로그인-서비스
    public Authorization.loginDto login() {
        return new Authorization.loginDto();
    }
    //TODO 중복아이디체크-서비스
    public Authorization.signupIdCheckDto signupIdCheck(String id) {
        return new Authorization.signupIdCheckDto();
    }
    //TODO 회원가입-서비스
    public Authorization.signupDto signup(String id, String password, String mobileCarrier, String phone) {
        return new Authorization.signupDto();
    }
}
