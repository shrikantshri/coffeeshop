package com.assessment.coffeeshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assessment.coffeeshop.service.CoffeeShopService;

/**
 * Controller of coffee shop to expose coffee shop API endpoints.
 *
 */
@Controller
@RequestMapping("coffeeshopservice/v1")
public class CoffeeShopController {
    private CoffeeShopService service;
    public CoffeeShopController(CoffeeShopService service) {
        this.service = service;
    }
    
    @GetMapping("/payments")
    @ResponseBody
    public List<Map<String, Object>> getPayment(){
        return service.getPayment();
    }
    @GetMapping("/payments/{user}")
    @ResponseBody
    public Map<String, Double> getPayment(@PathVariable String user){
        return service.getPayment(user);
    }
    
    @GetMapping("/balances")
    @ResponseBody
    public List<Map<String, Object>> getBalance() {
        return service.getBalance();
    }
    
    @GetMapping("/balances/{user}")
    @ResponseBody
    public Map<String, Double> getBalance(@PathVariable String user) {
        return service.getBalance(user);
    }
    
}
