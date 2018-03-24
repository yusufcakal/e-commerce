package com.yusufcakal.ecommerce.controller;

import com.yusufcakal.ecommerce.model.Category;
import com.yusufcakal.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * @return - get all categories
     * @throws EntityNotFoundException
     */
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<List<Category>> getCategories() throws EntityNotFoundException {
        List<Category> categoryList = (List<Category>) categoryRepository.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    /**
     * @param id - category id
     * @return - get single category
     * @throws EntityNotFoundException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<HttpStatus> getCategory(@PathVariable int id) throws EntityNotFoundException {
        Category category = categoryRepository.findOne((long) id);
        if (category.equals(null)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * @param category - category object
     * @return - HTTP OK - Register Success
     * @throws EntityNotFoundException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<List<Category>> addCategory(@RequestBody Category category) throws EntityNotFoundException {
        categoryRepository.save(category);
        List<Category> categoryList = (List<Category>) categoryRepository.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    /**
     * @param id - category id
     * @return - deleted category object and get all categories
     * @throws EntityNotFoundException
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity<List<Category>> deleteCategory(@PathVariable int id) throws EntityNotFoundException {
        categoryRepository.delete((long) id);
        List<Category> categoryList = (List<Category>) categoryRepository.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

}
