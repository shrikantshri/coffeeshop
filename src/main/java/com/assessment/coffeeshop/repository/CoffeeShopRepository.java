package com.assessment.coffeeshop.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * data access later for coffee shop application
 * retrieve data from class path resources [orders.json, payments.json, products.json]
 *
 */
@Repository
public class CoffeeShopRepository {
    
    final Logger log = LoggerFactory.getLogger(CoffeeShopRepository.class);
    @Autowired
    public ObjectMapper mapper;
    
    @Value("classpath:orders.json")
    private Resource orders;
    
    @Value("classpath:payments.json")
    private Resource payments;
    
    @Value("classpath:products.json")
    private Resource products;
    
    public List<Map<String, Object>> getOrders() {
        List<Map<String, Object>> orderList = new ArrayList<>();
        try {
            InputStream in = orders.getInputStream();
            orderList = mapper.readValue(in, List.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("Exception Occured while fetching and parsing given files : [orders]", e);
        }
        return orderList;
    }
    
    public List<Map<String, Object>> getPayments() {
        List<Map<String, Object>> paymentList = new ArrayList<>();
        try {
            InputStream in = payments.getInputStream();
            paymentList = mapper.readValue(in, List.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("Exception Occured while fetching and parsing given files : [payments]", e);
        }
        return paymentList;
    }
    
    public List<Map<String, Object>> getProducts() {
        List<Map<String, Object>> productList = new ArrayList<>();
        try {
            InputStream in = products.getInputStream();
            productList = mapper.readValue(in, List.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("Exception Occured while fetching and parsing given files : [products]", e);
        }
        return productList;
    }
}
