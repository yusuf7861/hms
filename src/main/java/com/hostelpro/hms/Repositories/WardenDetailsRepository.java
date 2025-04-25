package com.hostelpro.hms.repositories;

import com.hostelpro.hms.dto.WardenDetailsDto;
import com.hostelpro.hms.entities.WardenDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardenDetailsRepository extends JpaRepository<WardenDetails, Long>, JpaSpecificationExecutor<WardenDetails> {
}