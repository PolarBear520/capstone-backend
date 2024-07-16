package com.fdu.capstone.repository;

import com.fdu.capstone.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
