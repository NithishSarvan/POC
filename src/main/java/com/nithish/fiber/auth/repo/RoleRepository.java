package com.nithish.fiber.auth.repo;

import com.nithish.fiber.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> { }
