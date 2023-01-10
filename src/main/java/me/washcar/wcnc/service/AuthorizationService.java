package me.washcar.wcnc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto._StatusCodeDto;
import me.washcar.wcnc.entity.Role;
import me.washcar.wcnc.entity.SignupToken;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.exception.ErrorCode;
import me.washcar.wcnc.repository.RoleRepo;
import me.washcar.wcnc.repository.SignupTokenRepository;
import me.washcar.wcnc.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;
    private final RoleRepo roleRepository;
    private final SignupTokenRepository signupTokenRepository;
    private final PasswordEncoder passwordEncoder;

    private boolean isTokenValidate(String email, String token){
        SignupToken signupToken = signupTokenRepository.findByEmail(email).orElse(new SignupToken());

        LocalDateTime now = LocalDateTime.now();
        Boolean isRecent = signupToken.getModifiedDate().isAfter(now.minusMinutes(5));
        if(Objects.equals(signupToken.getToken(), token) && isRecent) {
            return true;
        } else {
            return false;
        }
    }
    public _StatusCodeDto signupCheckEmail(String email){

        User user = userRepository.findByEmail(email);
        if(user != null) return new _StatusCodeDto(1701, "이미 사용중인 이메일");

        SignupToken signupToken = signupTokenRepository.findByEmail(email).orElse(new SignupToken());

        Random rand = new Random();
        StringBuilder tokenBuilder = new StringBuilder();
        for(int i = 0; i < 6; i++){
            tokenBuilder.append(rand.nextInt(10));
        }
        String token = tokenBuilder.toString();

        signupToken.setEmail(email);
        signupToken.setToken(token);

        //TODO - sendEmail

        signupTokenRepository.save(signupToken);

        return new _StatusCodeDto(1700, "이메일 전송함");
    }

    public _StatusCodeDto signupCheckNumber(String email, String token) {
        if(isTokenValidate(email, token)) {
            return new _StatusCodeDto(2700, "인증번호 유효함");
        } else {
            return new _StatusCodeDto(2701, "인증번호 유효하지 않음");
        }
    }

    public _StatusCodeDto signup(String email, String token, String password) {
        if(isTokenValidate(email, token)) {
            User user = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .build();

            Role role = roleRepository.findByName("ROLE_USER");
            user.getRoles().add(role);
            userRepository.save(user);

            SignupToken signupToken = signupTokenRepository.findByEmail(email).orElseThrow(
                    () -> new CustomException(ErrorCode.SIGNUP_LOGIC_ERROR)
            );
            signupTokenRepository.delete(signupToken);

            return new _StatusCodeDto(1800, "회원가입 성공");
        } else {
            return new _StatusCodeDto(1801, "회원가입 실패");
        }
    }
}
