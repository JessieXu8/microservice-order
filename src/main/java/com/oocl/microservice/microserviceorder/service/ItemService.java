package com.oocl.microservice.microserviceorder.service;

import com.oocl.microservice.microserviceorder.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemService {
    @Autowired private RestTemplate restTemplate;

    @Value("${itcast.item.url}") private String itemUrl;

    public Item getItemById(Long id) {
        //sent HTTP request by RestTemplate
        //restTemplate.getForObject(url, response type); 反射
        return restTemplate.getForObject(itemUrl + id, Item.class);
    }
}
