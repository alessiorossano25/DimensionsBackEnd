package com.progetto.Dimensions.repository;



import com.progetto.Dimensions.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
User findByMail(String mail);
User findByUsername(String username);




}
