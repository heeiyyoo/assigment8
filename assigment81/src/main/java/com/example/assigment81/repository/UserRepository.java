package com.example.assigment81.repository;

import com.example.assigment81.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User getById(Integer Id);
    Boolean existsUserById(Integer Id);
}
