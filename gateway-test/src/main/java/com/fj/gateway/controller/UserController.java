package com.fj.gateway.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/gateway")
public class UserController {

    @GetMapping(value = "/test")
    public String Tset(){
        return "test";
    }

}
