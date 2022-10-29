package me.washcar.wcnc;

import me.washcar.wcnc.entity.Role;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.service._UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class WcncApplication {

    public static void main(String[] args) {
        SpringApplication.run(WcncApplication.class, args);
    }

    @Bean
    CommandLineRunner run(_UserService userService){
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "TestIdThis", "test@gmail.com", "password", "nicktest", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "hong", "hong@gmail.com", "hongPass", "gilt", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "memememe", "me@gmail.com", "meme", "meisme", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "wcncadmin", "admin@naver.com", "adminpassword", "ADMIN", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

            userService.addRoleToUser("TestIdThis", "ROLE_USER");
            userService.addRoleToUser("hong", "ROLE_MANAGER");
            userService.addRoleToUser("hong", "ROLE_USER");
            userService.addRoleToUser("hong", "ROLE_ADMIN");
            userService.addRoleToUser("memememe", "ROLE_ADMIN");
            userService.addRoleToUser("wcncadmin", "ROLE_ADMIN");
            userService.addRoleToUser("wcncadmin", "ROLE_SUPER_ADMIN");
        };
    }
}
