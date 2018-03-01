package com.yusufcakal.ecommerce.repository;

import com.yusufcakal.ecommerce.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {}
