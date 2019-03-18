package com.oocl.microservice.microserviceorder.service;

import com.oocl.microservice.microserviceorder.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ItemService {
    @Autowired private RestTemplate restTemplate;
//
//    @Value("${itcast.item.url}") private String itemUrl;
//
//    public Item getItemById(Long id) {
//        //sent HTTP request by RestTemplate
//        //restTemplate.getForObject(url, response type); 反射
//        return restTemplate.getForObject(itemUrl + id, Item.class);
//    }

    /**
     * 获取服务提供商所提供的一些信息
     * DiscoveryClient：使用spring cloud下面的DiscoveryClient而不是Netflix下面的DiscoveryClient，做了二次封装
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    public Item getItemById(Long id) {
        //指定商品微服务的名称,返回服务列表
        List<ServiceInstance> instances = discoveryClient.getInstances("micro-service-item");
        if (null == instances || instances.isEmpty()){
            return null;
        }
        //从服务列表中获取服务
        ServiceInstance serviceInstance = instances.get(0);
        //拿到这个service instance之后就可以调用它里面的方法
        //serviceInstance.getHost();拿到主机名
        //serviceInstance.getPort();拿到端口号
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        return restTemplate.getForObject(url + "/item/" + id, Item.class);
    }
}
