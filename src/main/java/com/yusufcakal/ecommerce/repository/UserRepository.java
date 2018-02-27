package com.yusufcakal.ecommerce.repository;

import com.yusufcakal.ecommerce.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

}
