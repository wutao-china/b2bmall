package com.legensity.b2bmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.legensity.b2bmall.**.dao")
public class B2bmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2bmallApplication.class, args);
    }

}
