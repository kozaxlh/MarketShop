package com.example.MarketShop.Repository;

import com.example.MarketShop.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;

public interface RolesRepository extends JpaRepository<Roles, Integer>, JpaSpecificationExecutor<Roles> {

}