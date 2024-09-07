package com.resetas.resetas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @PostMapping(value = "users")
    public String welcome() {
      return "Welcome from secure endpoint";
    }
    

}
