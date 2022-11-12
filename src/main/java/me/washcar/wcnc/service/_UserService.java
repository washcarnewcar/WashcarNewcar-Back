package me.washcar.wcnc.service;

import me.washcar.wcnc.entity.Role;
import me.washcar.wcnc.entity.User;

import java.util.List;

public interface _UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    User getUser(String userId);
    List<User>getUsers();
}
