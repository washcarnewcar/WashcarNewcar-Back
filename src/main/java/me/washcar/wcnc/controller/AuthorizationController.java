package me.washcar.wcnc.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.Authorization;
import me.washcar.wcnc.entity.Role;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.service.AuthorizationService;
import me.washcar.wcnc.service._UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Data
class RoleToUserForm {
    private String userId;
    private String roleName;
}

@RestController
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService authorizationService;
    private final _UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User>saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<Role>addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUserId(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public Authorization.loginDto login(){
        return authorizationService.login();
    }

    @GetMapping("/signup/check")
    public Authorization.signupIdCheckDto signupIdCheck(@RequestParam("id") String id) {
        return authorizationService.signupIdCheck(id);
    }

    @PostMapping("/signup")
    public Authorization.signupDto signup(){
        return authorizationService.signup();
    }

}
