package com.oocl.microservice.microserviceorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 声明这是一个Eureka的客户端
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceOrderApplication {
    //Default use HTTP Client
    //Use OK client
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceOrderApplication.class, args);
    }

}
