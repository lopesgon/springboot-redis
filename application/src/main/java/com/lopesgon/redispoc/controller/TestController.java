package com.lopesgon.redispoc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("v1")
@AllArgsConstructor
public class TestController {

    // final StringRedisTemplate redisTemplate;
    
    @GetMapping
    public String running() {
        return "It's UP!";
    }

    @GetMapping("/publish")
    public void pushMessage(@RequestParam String msg) {
        log.debug(String.format("Pushing message [%s] to Redis Pub/Sub",msg));
        // redisTemplate.convertAndSend("chat", msg);
    }
}
