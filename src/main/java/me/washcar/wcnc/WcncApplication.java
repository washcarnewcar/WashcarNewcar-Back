package me.washcar.wcnc;

import me.washcar.wcnc.service._UserService;
import me.washcar.wcnc.service.provider.StoreCreateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
@ConfigurationPropertiesScan("me.washcar.wcnc.config")
public class WcncApplication {

    public static void main(String[] args) {
        SpringApplication.run(WcncApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(_UserService userService, StoreCreateService storeCreateService) {
        return args -> {
            /*userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "test@gmail.com", "password", "nicktest",
                    new HashSet<>(), new HashSet<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "hong@gmail.com", "hongPass", "gilt", new HashSet<>(),
                    new HashSet<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "me@gmail.com", "meme", "meisme", new HashSet<>(),
                    new HashSet<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "admin@naver.com", "adminpassword", "ADMIN",
                    new HashSet<>(), new HashSet<>(), new ArrayList<>()));

            userService.addRoleToUser("test@gmail.com", "ROLE_USER");
            userService.addRoleToUser("hong@gmail.com", "ROLE_MANAGER");
            userService.addRoleToUser("hong@gmail.com", "ROLE_USER");
            userService.addRoleToUser("hong@gmail.com", "ROLE_ADMIN");
            userService.addRoleToUser("me@gmail.com", "ROLE_ADMIN");
            userService.addRoleToUser("admin@naver.com", "ROLE_USER");
            userService.addRoleToUser("admin@naver.com", "ROLE_MANAGER");
            userService.addRoleToUser("admin@naver.com", "ROLE_ADMIN");
            userService.addRoleToUser("admin@naver.com", "ROLE_SUPER_ADMIN");*/

        };
    }
}
