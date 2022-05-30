package io.jay.springbootfeigntest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBootFeignTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFeignTestApplication.class, args);
    }

}
