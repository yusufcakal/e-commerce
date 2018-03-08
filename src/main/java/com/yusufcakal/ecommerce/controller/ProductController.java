package com.yusufcakal.ecommerce.controller;

import com.yusufcakal.ecommerce.model.Product;
import com.yusufcakal.ecommerce.model.User;
import com.yusufcakal.ecommerce.repository.ProductRepository;
import com.yusufcakal.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/get")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(value = "token") int token){
        List<User> userList = (List<User>) userRepository.findAll();
        for (User user : userList) {
            if (user.getToken() == token){
                List<Product> productList = (List<Product>) productRepository.findAll();
                return new ResponseEntity<>(productList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<HttpStatus> getCategory(@PathVariable int id) throws EntityNotFoundException {
        Product product = productRepository.findOne((long) id);
        if (product.equals(null)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<List<Product>> addCategory(@RequestBody Product product) throws EntityNotFoundException {
        productRepository.save(product);
        System.out.println(product);
        List<Product> productList = (List<Product>) productRepository.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<List<Product>> updateCategory(@RequestBody Product product, @PathVariable long id) throws EntityNotFoundException {
        Product product1 = productRepository.findOne(id);
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setStock(product.getStock());
        productRepository.save(product1);
        System.out.println(product1);
        List<Product> productList = (List<Product>) productRepository.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable int id) throws EntityNotFoundException {
        productRepository.delete((long) id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
