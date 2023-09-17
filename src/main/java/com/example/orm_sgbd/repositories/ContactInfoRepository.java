package com.example.orm_sgbd.repositories;

import com.example.orm_sgbd.models.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, UUID> {
    Iterable<ContactInfo> findByUserIdUser(UUID id);
}
