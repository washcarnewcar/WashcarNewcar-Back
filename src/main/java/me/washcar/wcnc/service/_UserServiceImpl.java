package me.washcar.wcnc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.entity.Role;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.repository.RoleRepo;
import me.washcar.wcnc.repository.UserRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class _UserServiceImpl implements _UserService{
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} into DB", user.getUserLoginId());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} into DB", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userLoginId, String roleName) {
        log.info("Adding role {} to user {}", roleName, userLoginId);
        User user = userRepo.findByUserLoginId(userLoginId);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String userLoginId) {
        log.info("Fetching user {}", userLoginId);
        return userRepo.findByUserLoginId(userLoginId);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching users");
        return userRepo.findAll();
    }
}
