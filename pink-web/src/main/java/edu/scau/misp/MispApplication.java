package edu.scau.misp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//git config --system --unset credential.helper
@SpringBootApplication
public class MispApplication {
    public static void main(String[] args) {
        SpringApplication.run(MispApplication.class, args);
        System.out.println("MISP开发平台启动成功");
    }
}
