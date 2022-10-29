package me.washcar.wcnc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.entity.Role;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.repository.RoleRepo;
import me.washcar.wcnc.repository.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class _UserServiceImpl implements _UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String userLoginId) throws UsernameNotFoundException {
        User user = userRepo.findByUserLoginId(userLoginId);

        if(user == null){
            log.error("User not found in the DB");
            throw new UsernameNotFoundException("User not found in the DB");
        } else {
            log.info("User found in the database: {}", userLoginId);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUserLoginId(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} into DB", user.getUserLoginId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
