package com.example.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jiangls
 * @date 2021/11/22
 */
@Data
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Autowired
    private final RestTemplate restTemplate;

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

    @GetMapping(value = "/echo/{str}")
    public String echo(@PathVariable String str) {
        return "consumer from port " + this.port + " , " + restTemplate.getForObject("http://provider/config/echo/" + str, String.class);
    }
}
