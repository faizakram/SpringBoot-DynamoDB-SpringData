package com.example.demo.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.User;

@EnableScan
public interface UserCrudServiceRepository extends CrudRepository<User, String> {


}
