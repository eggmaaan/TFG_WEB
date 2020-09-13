package com.tfgshop.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * A traves de ese exclude, eliminamos las restricciones de Spring Security
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class OnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineApplication.class, args);
    }

}
