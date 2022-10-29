package me.washcar.wcnc.repository;

import me.washcar.wcnc.entity.Role;
import me.washcar.wcnc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
