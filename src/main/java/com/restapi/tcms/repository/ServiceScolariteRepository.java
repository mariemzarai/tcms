package com.restapi.tcms.repository;

import com.restapi.tcms.model.ServiceScolarite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceScolariteRepository extends JpaRepository<ServiceScolarite, Long> {
}
