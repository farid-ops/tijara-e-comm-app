package amiral.aokiji.tijara;

import amiral.aokiji.tijara.core.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TijaraApplication implements CommandLineRunner {

    private final UserService userService;

    public TijaraApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TijaraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        UserEntity user = new UserEntity();
//        user.setUsername("farid");
//        user.setPassword("farid");
//        this.userService.save(user);
    }
}
