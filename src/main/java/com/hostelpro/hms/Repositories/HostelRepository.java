package com.hostelpro.hms.Repositories;

import com.hostelpro.hms.Entities.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long>, JpaSpecificationExecutor<Hostel> {
}