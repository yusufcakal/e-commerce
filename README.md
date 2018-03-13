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

Tables (Coming Soon..)

### Services

- [User](https://github.com/yusufcakal/e-commerce#user)
- [Product](https://github.com/yusufcakal/e-commerce#product) 
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


