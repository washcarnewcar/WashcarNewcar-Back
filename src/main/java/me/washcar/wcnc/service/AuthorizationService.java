package me.washcar.wcnc.service;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.AuthorizationDto;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorizationService {
    //TODO 로그인-서비스
    public AuthorizationDto.login login() {
        return new AuthorizationDto.login();
    }
    //TODO 중복아이디체크-서비스
    public AuthorizationDto.signupIdCheck signupIdCheck(String id) {
        return new AuthorizationDto.signupIdCheck();
    }
    //TODO 회원가입-서비스
    public AuthorizationDto.signup signup() {
        return new AuthorizationDto.signup();
    }
}
