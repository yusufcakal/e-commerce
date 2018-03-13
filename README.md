![basliksiz-1](https://user-images.githubusercontent.com/16848490/37253080-c341dd9e-253d-11e8-8244-89c35f541604.png)


<h1 align="center"> Basic E-Commerce Service </h1> <br>
<p align="center">
It's open source project providers fundamental product and user management. 

---
</p>


## Table of Contents

- [Database Tables](https://github.com/yusufcakal/e-commerce#database-tables)
- [Services](https://github.com/yusufcakal/e-commerce#services)

### Database Tables

![ekran alintisi](https://user-images.githubusercontent.com/16848490/37373287-030cc5fc-2727-11e8-8282-2d0267350dd3.PNG)

### Services

- [User](https://github.com/yusufcakal/e-commerce#user)
  + [register](https://github.com/yusufcakal/e-commerce#register)
  + [login](https://github.com/yusufcakal/e-commerce#login)
  + [verify](https://github.com/yusufcakal/e-commerce#verify)
- [Product](https://github.com/yusufcakal/e-commerce#product)
  + [add](https://github.com/yusufcakal/e-commerce#add)
  + [edit](https://github.com/yusufcakal/e-commerce#edit)
  + [delete](https://github.com/yusufcakal/e-commerce#delete)
  + [get](https://github.com/yusufcakal/e-commerce#get)
- [Category](https://github.com/yusufcakal/e-commerce#category) 


#### User
 - ##### Register
     ```
    url: ../user/register | POST 
    ```
   - ###### request
   ```json
    {
        "name": "John Doe",
        "email": "info@johndoe.com",
        "password": "123456"
    }
    ```
    - ###### response
    ```
    if there is no user 201 (Http Created) other case already user 409 (Http Conflict)
    ```
    
 - ##### Login
     ```
    url: ../user/login | POST 
    ```
   - ###### request
   ```json
    {
        "email": "info@johndoe.com",
        "password": "123456"
    }
    ```
    - ###### response
    ```
    200 (Http Ok), if token value -1 is user unauthorized.
    ```
 - ##### Verify
     ```
    url: ../user/verify/{token} | GET 
    ```
   - ###### request
   ```
   User verify with email.
    ```
    - ###### response
    ```
    200 (Http Ok) will other requests sending user token.
    ```

#### Product
 - ##### Add
     ```
    url: ../products/add | POST 
    ```
   - ###### request
   ```json
    {
      "name": "New Product",
      "price": 60.00,
      "stock": 30,
      "category": {
                  "id": 1
             }
    }
    MultipartFile[] product images.
    ```
    - ###### response
    ```
    200 (Http Ok) and productlist
    ```
    
 - ##### Edit
     ```
    url: ../products/{productId} | PUT 
    ```
    - ###### request
    ```
    Product Object
    ```
    - ###### response
    ```
    200 (Http Ok) and productlist
    ```
 - ##### Delete
     ```
    url: ../products/{productId} | DELETE 
    ```
   - ###### request
   ```
   Product id send on url path variable.
    ```
    - ###### response
    ```
    200 (Http Ok) is product edited.
    ```
 - ##### Get
     ```
    url: ../products/{productId} | GET
    ```
    - ###### request
    ```
    Get request also if there is no productId all products. ../category/{category_id} products of category.
    ```
    - ###### response
    ```
    200 (Http Ok) product or productlist
    ```
 - ##### Images
     ```
    url: ../products//images/{productId} | GET 
    ```
   - ###### request
   ```
   Product id send on url path variable.
    ```
    - ###### response
    ```
    200 (Http Ok) is images of product.
    ```


