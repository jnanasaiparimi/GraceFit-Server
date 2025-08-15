package com.jnanasai.Security.Service;

import com.jnanasai.Security.Model.Order;
import com.jnanasai.Security.Model.OrderItem;
import com.jnanasai.Security.Model.Product;
import com.jnanasai.Security.Model.Users;
import com.jnanasai.Security.Repo.OrderRepo;
import com.jnanasai.Security.Repo.ProductRepository;
import com.jnanasai.Security.Repo.Repo;  // Users repo
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private Repo userRepo;
    
    @Autowired
    private ProductRepository productRepository;
  
    /**
     * Save new order for a user.
     * @param username user's username
     * @param items list of order items
     * @param totalPrice total price of the order
     * @return saved Order
     */
    public Order saveOrder(String username, List<OrderItem> items, Double totalPrice) {
        Users user = userRepo.findByUsername(username);

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Confirmed");
        order.setTotalPrice(totalPrice);

        for (OrderItem item : items) {
            item.setOrder(order);

            int  productId = item.getProduct().getId();
            Product product = productRepository.findById(productId).orElse(null);

            item.setProduct(product); // set the actual Product object
            if (product != null) {
                item.setProductName(product.getName()); // ✅ manually store name
            }
            
        }
 
        order.setOrderItems(items);

        return orderRepo.save(order);
    }

    /**
     * Fetch orders for a user by username.   
     */
    public List<Order> getOrdersByUser(String username) {
        Users user = userRepo.findByUsername(username);
        return orderRepo.findByUser(user);
    }
}
