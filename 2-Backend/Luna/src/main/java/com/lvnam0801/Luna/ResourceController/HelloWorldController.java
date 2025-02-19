package com.lvnam0801.Luna.ResourceController;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RestController;

import com.lvnam0801.Luna.ResourceRepresentation.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Resource Representation Class
@RestController
public class HelloWorldController {
    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    @GetMapping("/hello")
    public Hello hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        long id = counter.incrementAndGet();
        String content = String.format(template, name);
        return new Hello(id, content);
    }
}