package com.yusufcakal.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yusufcakal.ecommerce.model.Image;
import com.yusufcakal.ecommerce.model.Product;
import com.yusufcakal.ecommerce.repository.ImageRepository;
import com.yusufcakal.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageRepository imageRepository;

    private static final String UPLOADED_FOLDER = "C:\\Users\\Yusuf\\Desktop\\Java Pojects\\ecommerce\\src\\main\\resources\\upload\\";

    /**
     * @return return all registered products in the system.
     */
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> productList = (List<Product>) productRepository.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     * @param id - user id
     * @return - user entity
     * @throws EntityNotFoundException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) throws EntityNotFoundException {
        Product product = productRepository.findOne((long) id);
        if (product.equals(null)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    /**
     *
     * @param category_id - get products category id
     * @return - get all products in the category
     * @throws EntityNotFoundException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/category/{category_id}")
    public ResponseEntity<?> getProductsOfCategory(@PathVariable int category_id) throws EntityNotFoundException {
        List<Product> productList = (List<Product>) productRepository.findAll();
        List<Product> productsOfCategoryList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory_id() == category_id){
                productsOfCategoryList.add(product);
            }
        }
        return new ResponseEntity<>(productsOfCategoryList, HttpStatus.OK);
    }

    /**
     *
     * @param id - product id
     * @return - get all images of product
     * @throws EntityNotFoundException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/images/{id}")
    public ResponseEntity<?> getImagesOfProduct(@PathVariable int id) throws EntityNotFoundException {
        List<Image> imageList = (List<Image>) imageRepository.findAll();
        List<Image> imagesOfProductList = new ArrayList<>();
        for (int i=0; i<imageList.size(); i++){
            if (imageList.get(i).getProduct_id() == id){
                imagesOfProductList.add(imageList.get(i));
            }
        }
        return new ResponseEntity<>(imagesOfProductList, HttpStatus.OK);
    }

    /**
     *
     * @param files - product's image or images type multipart
     * @param strPojo - standart product json request
     * @return - get all products in the system
     * @throws EntityNotFoundException
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<List<Product>> addProduct(@RequestParam(value = "file") MultipartFile[] files,
                                                     @RequestParam(value = "product") String strPojo) throws EntityNotFoundException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(strPojo, Product.class);
        System.out.println(strPojo);
        productRepository.save(product);

        for (MultipartFile file : files) {

            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);

            Image image = new Image(file.getOriginalFilename(), path.toString(), (int) product.getId());
            imageRepository.save(image);
        }

        List<Product> productList = (List<Product>) productRepository.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     * @param product - passing editable product object
     * @param id - the id of edit product object
     * @return - edited product object
     * @throws EntityNotFoundException
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<List<Product>> updateProduct(@RequestBody Product product, @PathVariable long id) throws EntityNotFoundException {
        Product product1 = productRepository.findOne(id);
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setStock(product.getStock());
        productRepository.save(product1);
        System.out.println(product1);
        List<Product> productList = (List<Product>) productRepository.findAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    /**
     * @param id -  product id
     * @return - HTTP OK - deleted product object
     * @throws EntityNotFoundException - does not exits product object
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int id) throws EntityNotFoundException {
        productRepository.delete((long) id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
