package com.example.MarketShop.Repository;

import com.example.MarketShop.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}