package edu.scau.misp.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hairsalon")
public class IndexController {
    @GetMapping("/index")
    public String index(){
        return "Hello SpringBoot";
    }

}
