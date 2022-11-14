package me.washcar.wcnc;

import me.washcar.wcnc.entity.Role;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.service._UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class WcncApplication {

  public static void main(String[] args) {
    SpringApplication.run(WcncApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

    /*@Bean
    CommandLineRunner run(_UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "test@gmail.com", "password", "nicktest",
                    new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "hong@gmail.com", "hongPass", "gilt", new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "me@gmail.com", "meme", "meisme", new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "admin@naver.com", "adminpassword", "ADMIN",
                    new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

            userService.addRoleToUser("test@gmail.com", "ROLE_USER");
            userService.addRoleToUser("hong@gmail.com", "ROLE_MANAGER");
            userService.addRoleToUser("hong@gmail.com", "ROLE_USER");
            userService.addRoleToUser("hong@gmail.com", "ROLE_ADMIN");
            userService.addRoleToUser("me@gmail.com", "ROLE_ADMIN");
            userService.addRoleToUser("admin@naver.com", "ROLE_USER");
            userService.addRoleToUser("admin@naver.com", "ROLE_MANAGER");
            userService.addRoleToUser("admin@naver.com", "ROLE_ADMIN");
            userService.addRoleToUser("admin@naver.com", "ROLE_SUPER_ADMIN");
        };
    }*/
}
