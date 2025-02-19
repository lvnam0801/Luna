package com.lvnam0801.Luna.ResourceController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting2Controller {
    public static final String template = "Greeting, %s!";

    @GetMapping("/greeting2")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) 
    {
        String response = String.format(template, name);
        return response;
    }
}
