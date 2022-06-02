package com.restapi.tcms.repository;

import com.restapi.tcms.model.ServiceStagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceStagiaireRepository extends JpaRepository<ServiceStagiaire,Long> {
}
