package com.mihir.simpleWebApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String greet() {
        return "Hello Welcome To Spring";
    }

    @RequestMapping("/about")
    public String about() {
        return "you are in about page";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "you are in contact page";
    }
}
