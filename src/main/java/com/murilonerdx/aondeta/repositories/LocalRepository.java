package com.murilonerdx.aondeta.repositories;

import com.murilonerdx.aondeta.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local,Long> {
}
