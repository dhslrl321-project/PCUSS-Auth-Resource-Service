package kr.ac.pcu.cyber.authresourceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthResourceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthResourceServiceApplication.class, args);
    }

}
