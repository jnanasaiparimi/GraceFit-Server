package com.jnanasai.Security.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jnanasai.Security.Model.Product;
import com.jnanasai.Security.Repo.ProductRepository;
import com.jnanasai.Security.Service.ProductService;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repo;

    // Get all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> display() {
        return ResponseEntity.ok(service.displayService());
    }

    // Get a single product
    @GetMapping("/products/{proId}")
    public ResponseEntity<Product> displaySinglePro(@PathVariable int proId) {
        Product product = service.displayServiceForSinglePro(proId);
        return product != null
                ? ResponseEntity.ok(product)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get product image
    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int id) {
        Product product = service.displayServiceForSinglePro(id);
        if (product != null && product.getImageData() != null) {
            return ResponseEntity.ok()
                    .header("Content-Type", product.getImageType())
                    .body(product.getImageData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add product (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/product")
    public ResponseEntity<?> addProductData(
            @RequestPart("product") Product product,
            @RequestPart("imageData") MultipartFile imageData) {
        try {
            Product saved = service.addProductData(product, imageData);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Add failed: " + e.getMessage());
        }
    }

    // Update product (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable int id,
            @RequestPart("product") Product product,
            @RequestPart("imageData") MultipartFile imageData) {
        try {
            Product updated = service.updateProductDetails(id, product, imageData);
            if (updated != null) {
                return ResponseEntity.ok("Product updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update failed.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Delete product (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/productd/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        boolean success = service.deleteProductFromId(id);
        return success
                ? ResponseEntity.ok("Product deleted successfully.")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
    }

    // Search product
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword) {
        List<Product> found = service.searchProducts(keyword);
        return ResponseEntity.ok(found);
    }
}
