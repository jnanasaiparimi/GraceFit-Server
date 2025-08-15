package com.jnanasai.Security.Controller;

import com.jnanasai.Security.Model.Order;
import com.jnanasai.Security.Model.OrderItem;
import com.jnanasai.Security.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Fetch all orders for a user
    @GetMapping("/user/{username}")
    public List<Order> getOrdersByUser(@PathVariable String username) {
        return orderService.getOrdersByUser(username);
    }

    // Create a new order for user (call after checkout/payment)
    @PostMapping("/create/{username}")
    public Order createOrder(@PathVariable String username, @RequestBody Order order) {
        List<OrderItem> items = order.getOrderItems();
        Double totalPrice = order.getTotalPrice();
        return orderService.saveOrder(username, items, totalPrice);
    }
}
                    