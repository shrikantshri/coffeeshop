package com.assessment.coffeeshop.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.assessment.coffeeshop.repository.CoffeeShopRepository;

@SpringBootTest
public class CoffeeShopRepositoryTest {
    @Autowired
    private CoffeeShopRepository repository;
    @Test
    public void getOrdersTest() {
        List<Map<String, Object>> orders = repository.getOrders();
        assertEquals(orders.size(), 100);
    }
    
    @Test
    public void getPaymentsTest() {
        List<Map<String, Object>> orders = repository.getPayments();
        assertEquals(orders.size(), 20);
    }
    
    @Test
    public void getProductsTest() {
        List<Map<String, Object>> orders = repository.getProducts();
        assertEquals(orders.size(), 6);
    }
}
