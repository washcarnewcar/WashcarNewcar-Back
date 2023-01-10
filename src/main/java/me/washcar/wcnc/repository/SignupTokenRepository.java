package me.washcar.wcnc.repository;

import me.washcar.wcnc.entity.SignupToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignupTokenRepository extends JpaRepository<SignupToken, Long> {
    Optional<SignupToken> findByEmail(String email);
}
