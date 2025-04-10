package com.hostelpro.hms.Repositories;

import com.hostelpro.hms.Entities.WardenDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WardenDetailsRepository extends JpaRepository<WardenDetails, Long>, JpaSpecificationExecutor<WardenDetails> {
}