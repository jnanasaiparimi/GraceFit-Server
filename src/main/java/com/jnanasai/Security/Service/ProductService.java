package com.jnanasai.Security.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jnanasai.Security.Model.Product;
import com.jnanasai.Security.Repo.ProductRepository;

import io.jsonwebtoken.io.IOException;

@Service

public class ProductService
{
	@Autowired
	ProductRepository repo;
	
	public List<Product> displayService()
	{
		return repo.findAll();
		
	}

	public Product displayServiceForSinglePro(int proId)
	{
		return repo.findById(proId).orElse(null);
	}
	
	public Product addProductData(Product product, MultipartFile imageData) throws IOException, java.io.IOException {
	    product.setImageName(imageData.getOriginalFilename());
	    product.setImageType(imageData.getContentType());
	    product.setImageData(imageData.getBytes());
	    return repo.save(product);
	}
     
	public Product updateProductDetails(int id, Product product, MultipartFile imageData) throws IOException, java.io.IOException 
	{
		product.setImageData(imageData.getBytes());
		product.setImageName(imageData.getOriginalFilename());
		product.setImageType(imageData.getContentType());
		return repo.save(product);
	}

	public boolean deleteProductFromId(int id) 
	{
		if(repo.existsById(id)) 
		{			
		   repo.deleteById(id);
		   return true;
	    }
		else
		{
			return false;
		}
	}

	public List<Product> searchProducts(String keyword) 
	{
		return repo.searchProduct(keyword);
	}
	
	
}
