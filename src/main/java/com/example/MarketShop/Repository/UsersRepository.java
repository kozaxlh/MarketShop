package com.example.MarketShop.Repository;

import com.example.MarketShop.Model.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends GenericRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}