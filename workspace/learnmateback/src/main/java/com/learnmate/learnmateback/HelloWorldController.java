package com.learnmate.learnmateback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping("/getAllUsuarios")
    public List<Usuario> getAllUsuarios() {
        return helloWorldService.getAllUsuarios();
    }
}
