package com.example.MarketShop.Repository;

import com.example.MarketShop.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolesRepository extends JpaRepository<Roles, Integer>, JpaSpecificationExecutor<Roles> {
    Roles findByRoleName(String roleName);
}