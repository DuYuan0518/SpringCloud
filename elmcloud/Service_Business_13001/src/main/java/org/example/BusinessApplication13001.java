package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//开启Spring Cloud Feign的支持功能
@EnableFeignClients
public class BusinessApplication13001 {
    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication13001.class, args);
    }
}
