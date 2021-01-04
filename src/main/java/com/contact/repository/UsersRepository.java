package com.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contact.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {
	
	@Query("select u from Users u where u.email = :email")
	public Users getUserByName(@Param("email") String email);

}
