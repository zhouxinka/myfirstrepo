package com.example.phoneinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example")
@MapperScan("com.example.info.dao")
@SpringBootApplication
public class PhoneinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneinfoApplication.class, args);

    }

}
