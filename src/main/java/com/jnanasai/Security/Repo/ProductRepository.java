package com.jnanasai.Security.Repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jnanasai.Security.Model.Product;


@Repository


public interface ProductRepository extends JpaRepository<Product, Integer>
{
	
	@Query("SELECT p FROM Product p WHERE " +
		       "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))"
		       )
	 List<Product> searchProduct(String keyword);
}
