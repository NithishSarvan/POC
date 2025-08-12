package com.nithish.fiber.coirunit.repo;


import com.nithish.fiber.coirunit.entity.CoirWorker;
import com.nithish.fiber.coirunit.entity.CoirWorkerWageEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoirWorkerWageEntryRepository extends JpaRepository<CoirWorkerWageEntry, Long> { }
