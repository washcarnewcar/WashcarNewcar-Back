package me.washcar.wcnc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

public class AuthorizationDto {
    //TODO 로그인-Dto
    @Getter
    public static class login{
        private String id;
        private String password;
    }
    //TODO 중복아이디체크-Dto
    @Getter
    public static class signupIdCheck{
        private int state;
        private String message;
    }
    //TODO 회원가입-Dto
    @Getter
    public static class signup{
        private int state;
        private String message;
        private String token;
    }
}
