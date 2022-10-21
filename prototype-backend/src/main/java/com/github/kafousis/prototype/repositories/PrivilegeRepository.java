package com.github.kafousis.prototype.repositories;

import com.github.kafousis.prototype.entities.Privilege;
import com.github.kafousis.prototype.enums.PrivilegeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    boolean existsPrivilegeByName(String name);
    Optional<Privilege> findByName(String name);
    List<Privilege> findByCategory(PrivilegeCategory category);
}
