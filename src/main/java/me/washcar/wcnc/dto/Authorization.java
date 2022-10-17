package me.washcar.wcnc.dto;

import lombok.Getter;

public class Authorization {
    //TODO 로그인-Dto
    @Getter
    public static class loginDto {
        private String id;
        private String password;
    }
    //TODO 중복아이디체크-Dto
    @Getter
    public static class signupIdCheckDto {
        private int state;
        private String message;
    }
    //TODO 회원가입-Dto
    @Getter
    public static class signupDto {
        private int state;
        private String message;
        private String token;
    }
}
