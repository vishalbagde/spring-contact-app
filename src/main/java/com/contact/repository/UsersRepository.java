package com.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contact.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {

}
