package com.assessment.coffeeshop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.assessment.coffeeshop.repository.CoffeeShopRepository;



/**
 * Service layer of coffee shop application.
 *
 */
@Service
public class CoffeeShopService {

    private CoffeeShopRepository repo;

    public CoffeeShopService(CoffeeShopRepository repo) {
        this.repo = repo;
    }

    /**
     * @return List of payment made by each user
     */
    public List<Map<String, Object>> getPayment() {
        Map<String, Double> userPaymentMap = getUserPaymentMap();
        return mapToList(userPaymentMap);
    }
    
    /**
     * @param user : user name
     * @return amount paid by user
     */
    public Map<String, Double> getPayment(String user) {
        Map<String, Double> userPaymentMap = getUserPaymentMap();
        if(userPaymentMap.containsKey(user)) {
           return amountToMap(userPaymentMap.get(user));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment does not exist for User : "+user);
    }
    
    /**
     * @return key, value pair of user and their corresponding payments
     */
    private Map<String, Double> getUserPaymentMap(){
        List<Map<String, Object>> payments = repo.getPayments();
        return payments.stream().collect(Collectors.toMap(payment->(String)payment.get("user"), payment->Double.valueOf(payment.get("amount").toString()), (payment1,payment2)->payment1+payment2));
    }

    /**
     * @return List of balance due on each user
     * negative value refers to extra payment than their order
     */
    public List<Map<String, Object>> getBalance() {
        Map<String, Double> userBalanceMap = getUserBalanceMap();
        return mapToList(userBalanceMap);
    }
    
    /**
     * @param user : user name
     * @return balance due on given user
     * negative value refers to extra payment than their order
     */
    public Map<String, Double>  getBalance(String user) {
        Map<String, Double> userBalanceMap = getUserBalanceMap();
        if(userBalanceMap.containsKey(user)) {
            return amountToMap(userBalanceMap.get(user));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Balance does not exist for User : "+user);
    }
    
    /**
     * @return key, value pair of user and their corresponding balance
     */
    private  Map<String, Double> getUserBalanceMap() {
        Map<String, Double> userOrderpriceMap = getUserOrderpriceMap();
        Map<String, Double> userPaymentMap = getUserPaymentMap();
        userPaymentMap.forEach((user, amount) -> userOrderpriceMap.merge(user, amount, (orderAmount, paidAmount) -> orderAmount - paidAmount));
        return userOrderpriceMap;
    }

    /**
     * @return key, value pair of user and their conolidated order price
     */
    private Map<String, Double> getUserOrderpriceMap() {
        List<Map<String, Object>> orders = repo.getOrders();
        List<Map<String, Object>> products = repo.getProducts();
        Map<String, Double> userOrderPriceMap = orders.stream().map(order -> {
            order.put("price", getOrderPrice(order, products));
            return order;
        }).collect(Collectors.toMap(order -> (String) order.get("user"), order -> (Double) order.get("price"), 
                (orderPrice1, orderPrice2) -> orderPrice1 + orderPrice2));
        return userOrderPriceMap;
    }
    
    /**
     * @param order : individual order
     * @param products : List of all products
     * @return Price of given order
     */
    private double getOrderPrice(Map<String, Object> order, List<Map<String, Object>> products) {
        double price =  products.stream()
                .filter(product -> product.get("drink_name").equals(order.get("drink")))
                .map(product -> (((Map<String, Double>) product.get("prices")).get(order.get("size"))))
                .reduce(0.0, (price1, price2) -> price1 + price2);
        return price;
    }

    /**
     * @param map : user and amount map
     * @return List : more readable list of Users and their corresponding balance or payment
     */
    private List<Map<String, Object>> mapToList(Map<String,Double> map) {
        List<Map<String, Object>> list = new ArrayList<>();
        map.forEach((key, value) -> {
            Map<String, Object> innerMap = new HashMap<String, Object>();
            innerMap.put("user", key);
            innerMap.put("amount", value);
            list.add(innerMap);
        });
        return list;
    }
    
    /**
     * @param amount : amount in java.lang.Double
     * @return more readable key, value pair for amount and corresponding value
     */
    private Map<String, Double> amountToMap(Double amount) {
        Map<String, Double> amountMap = new HashMap<String, Double>();
        amountMap.put("amount", amount);
        return amountMap;
    }
    
}
