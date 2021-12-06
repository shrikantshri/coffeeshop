package com.assessment.coffeeshop.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.assessment.coffeeshop.service.CoffeeShopService;

@SpringBootTest
public class CoffeeShopServiceTest {
    @Autowired
    private CoffeeShopService service;
    
    
    @Test
    public void getPaymentTest() {
        List<Map<String,Object>> plyments = service.getPayment();
        assertEquals(plyments.size(), 8);
    }
    
    @Test
    public void getPaymentByUserTest() {
        Map<String, Double> payment = service.getPayment("zoey");
        assertEquals(payment.get("amount"), 101.0);
    }
    
    @Test
    public void getBalanceTest() {
        List<Map<String,Object>> plyments = service.getBalance();
        assertEquals(plyments.size(), 8);
    }
    
    @Test
    public void getBalanceByUserTest() {
        Map<String, Double> balance = service.getBalance("coach");
        assertEquals(balance.get("amount"), 4.0);
    }
    
    @Test
    public void getPaymentByUserTestException() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, ()-> service.getPayment("dummy"));
        assertEquals(ex.getMessage(), "404 NOT_FOUND \"Payment does not exist for User : dummy\"");
    }
    
    @Test
    public void getBalanceByUserTestException() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, ()-> service.getBalance("dummy"));
        assertEquals(ex.getMessage(), "404 NOT_FOUND \"Balance does not exist for User : dummy\"");
    }
}
