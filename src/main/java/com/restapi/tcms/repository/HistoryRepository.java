package com.restapi.tcms.repository;

import com.restapi.tcms.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {
}
