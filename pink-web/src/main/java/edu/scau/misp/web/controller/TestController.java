package edu.scau.misp.web.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String index(){
        return "Hello SpringSecurity";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/test1")
    public String test1(){
        return "authorize ADMIN request";
    }

    @PreAuthorize("hasAuthority('user')")
    @GetMapping("/test2")
    public String test2(){
        return "authorize USER request";
    }

    @PreAuthorize("hasAnyAuthority('admin','user')")
    @GetMapping("/test3")
    public String test3(){
        return "authorize ADMIN or USER request";
    }
}


