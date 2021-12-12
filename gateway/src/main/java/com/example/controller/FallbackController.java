package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiangls
 * @date 2021/12/6
 */
@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public String fallback() {
        return "fallback";
    }
}
