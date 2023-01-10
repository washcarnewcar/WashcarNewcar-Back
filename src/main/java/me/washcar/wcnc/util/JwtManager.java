package me.washcar.wcnc.util;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import me.washcar.wcnc.config.JwtConfiguration;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtManager {
    private final JwtConfiguration jwtConfiguration;
    private final Algorithm algorithm;

    public JwtManager(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
        this.algorithm = Algorithm.HMAC256(jwtConfiguration.getSecret().getBytes());
    }
}
