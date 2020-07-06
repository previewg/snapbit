package com.hgr.mini1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Mini1Application {

    public static void main(String[] args) {
        SpringApplication.run(Mini1Application.class, args);
    }

}
