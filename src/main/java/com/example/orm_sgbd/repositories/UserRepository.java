package com.example.orm_sgbd.repositories;

import com.example.orm_sgbd.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
	Iterable<User> findByContactInfoIdContactInfo(UUID id);
	
}
