package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiangls
 * @date 2021/11/22
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @Value("${spring.application.name:default}")
    private String applicationName;

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }

    @RequestMapping("/getName")
    public String getName() {
        return "i am " + this.applicationName + " from port " + this.port;
    }

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        return "hello " + string + ", i am provider from prot " + this.port;
    }
}
