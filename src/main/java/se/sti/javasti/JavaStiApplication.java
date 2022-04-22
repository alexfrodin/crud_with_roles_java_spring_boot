package se.sti.javasti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JavaStiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaStiApplication.class, args);
    }

    //@Bean
    //public void runThisAtStartUpToGetHashedPassword() {
    //    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //    String password = passwordEncoder.encode("test");
    //    System.out.println(password);
    //}

}
