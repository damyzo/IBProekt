package com.ib.demo.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredServerController {

    @RequestMapping("/secured")
    public String secured(){
        System.out.println("Inside secured()");
        return "Hello user !!! : ";
    }
}
