package com.hostelpro.hms.repositories;

import com.hostelpro.hms.entities.Hostel;
import com.hostelpro.hms.mapper.HostelDtoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long>, JpaSpecificationExecutor<Hostel> {
    List<HostelDtoInfo> findAllBy();
}