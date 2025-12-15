package com.aulaspring.aulaspring.repository;

import com.aulaspring.aulaspring.entity.User;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, UUID> {

}
