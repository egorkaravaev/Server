package com.server.demo.controller;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class Controller {

    Logger logger = (Logger) LoggerFactory.getLogger(Controller.class);

    @GetMapping("/")
    public String hello(){
        logger.info("hello");
        return "hello";
    }
}
