package com.github.kafousis.prototype.repositories;

import com.github.kafousis.prototype.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsRoleByName(String name);
    Optional<Role> findByName(String name);
}
