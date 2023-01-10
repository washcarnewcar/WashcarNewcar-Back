package me.washcar.wcnc.form;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class SignupCheckNumberForm {
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    private String number;
}
