package me.washcar.wcnc.form;

import lombok.Data;

@Data
public class SignUpForm {
    private String id;
    private String password;
    private String mobile_carrier;
    private String phone;
}