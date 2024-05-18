package com.learnmate.learnmateback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloWorldService {

    @Autowired
    private HelloWorldRepository helloWorldRepository;

    public List<Usuario> getAllUsuarios() {
        return helloWorldRepository.findAll();
    }
}
