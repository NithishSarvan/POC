package com.kali.fiber.repository;

import com.kali.fiber.entity.LaborWages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaborWagesRepository extends JpaRepository<LaborWages, Long> {
    List<LaborWages> findByLaborId(Long laborId);

}