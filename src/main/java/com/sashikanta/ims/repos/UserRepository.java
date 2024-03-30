package com.sashikanta.ims.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sashikanta.ims.entities.User;
import com.sashikanta.ims.util.UserRole;



public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByEmail(String email);

	List<User> findByRole(UserRole manager);


}
