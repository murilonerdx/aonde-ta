package com.murilonerdx.aondeta.repositories;

import com.murilonerdx.aondeta.entities.Profile;
import com.murilonerdx.aondeta.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
