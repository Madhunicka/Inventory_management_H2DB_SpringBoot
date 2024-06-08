package com.example.projectCRUD.restapi.controller;

import com.example.projectCRUD.restapi.entity.Product;
import com.example.projectCRUD.restapi.entity.Parts;
import com.example.projectCRUD.restapi.repository.PartsRepository;
import com.example.projectCRUD.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PartsRepository partsRepository;

    // Retrieve all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Retrieve a product by its ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Retrieve parts assigned to a product by product ID
    @GetMapping("/{id}/parts")
    public Set<Parts> getPartsByProductId(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return product != null ? product.getParts() : null;
    }

    // Create or update a product
    @PostMapping("/add")
    public Product createOrUpdateProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    // Update an existing product
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setInv(updatedProduct.getInv());
//            existingProduct.setParts(updatedProduct.getParts());
            System.out.println(existingProduct);
            productRepository.save(existingProduct);
        }
        return existingProduct;
    }

    // Delete a product by its ID
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    //http://localhost:8081/products/${selectedProductId}/addPart/${partId}
    @PutMapping("/{productId}/addPart/{partId}")
    public Product addPartToProduct(@PathVariable Long productId, @PathVariable Long partId) {
        Product product = productRepository.findById(productId).orElse(null);
        Parts part = partsRepository.findById(partId).orElse(null);

        if (product != null && part != null) {
            product.getParts().add(part);
            System.out.println("Before updating inventory: Product inventory = " + product.getInv() + ", Part inventory = " + part.getInv());
            product.setPrice(product.getPrice() + part.getPrice());
            product.setInv(product.getInv() + part.getInv());
            productRepository.save(product);
            System.out.println("Before updating inventory: Product inventory = " + product.getInv() + ", Part inventory = " + part.getInv());
        }

        return product;
    }

    //http://localhost:8081/products/${selectedProductId}/removePart/${partId}
    @PutMapping("/{productId}/removePart/{partId}")
    public Product removePartFromProduct(@PathVariable Long productId, @PathVariable Long partId) {
        // Fetch the product
        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {
            // Fetch the part
            Parts part = partsRepository.findById(partId).orElse(null);

            if (part != null) {
                // Check if the part is associated with the product
                if (product.getParts().removeIf(p -> p.getId().equals(partId))) {
                    // Update the inventory by subtracting the part's inventory
                    product.setInv(product.getInv() - part.getInv());
                    product.setPrice(product.getPrice()-part.getPrice());
                    // Save the updated product
                    productRepository.save(product);
                }
            }
        }
        return product;
    }
}