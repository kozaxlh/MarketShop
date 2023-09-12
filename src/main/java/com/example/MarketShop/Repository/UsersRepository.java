package com.example.MarketShop.Repository;

import com.example.MarketShop.Model.Users;

import java.util.Optional;

public interface UsersRepository extends GenericRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}