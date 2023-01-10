package me.washcar.wcnc.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SignupForm {
    private String number;

    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,20}$")
    private String password;
}