package com.murilonerdx.aondeta.repositories;

import com.murilonerdx.aondeta.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository  extends JpaRepository<Report,Long> {
}
