package com.jnanasai.Security.Repo;

import com.jnanasai.Security.Model.Order;
import com.jnanasai.Security.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUser(Users user);
}
