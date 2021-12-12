package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Jiangls
 * @date 2021/11/30
 */
@SpringBootApplication
//@EnableConfigurationProperties(Application.UriConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    /**
     * The Spring Cloud Gateway uses routes to process requests to downstream services.
     * In this guide, we route all of our requests to HTTPBin.
     * Routes can be configured a number of ways, but, for this guide, we use the Java API provided by the Gateway.
     *
     * The Spring Cloud Gateway使用routes处理对下游服务的请求。
     * 在这个guide中，我们route所有请求至HTTPBin。
     * Routes可以有好几种方式配置，但是，在这个guide中，我们使用the Gateway提供的Java API
     *
     * @param builder
     * @return
     */
//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes().build();
//    }

    /**
     * we can create a route that routes a request to https://httpbin.org/get when a request is made to the Gateway at /get.
     * In our configuration of this route,
     * we add a filter that adds the Hello request header with a value of World to the request before it is routed:
     *
     * 我们可以创建一个路由请求至https://httpbin.org/get的路由，当请求的是Gateway /get路径。
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator myRoutes2(RouteLocatorBuilder builder) {
        String httpUri = "http://httpbin.org:80";

        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri(httpUri))
                .route(p -> p
                        .host("*.circuitbreaker.com")
                        .filters(f -> f.circuitBreaker(config -> config.setName("mycmd").setFallbackUri("forward:/fallback")))
                        .uri(httpUri)
                ).build();
    }


//    @Component
//    @ConfigurationProperties(prefix = "spring")
//    public class UriConfiguration {
//
//        private String httpbin = "http://httpbin.org:80";
//
//        public String getHttpbin() {
//            return httpbin;
//        }
//
//        public void setHttpbin(String httpbin) {
//            this.httpbin = httpbin;
//        }
//    }

    /**
     * 一个fallback controller
     */
    @RestController
    public class Controller {
        @RequestMapping("/fallback")
        public Mono<String> fallback() {
            return Mono.just("fallback");
        }
    }


}
