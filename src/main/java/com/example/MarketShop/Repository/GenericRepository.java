package com.example.MarketShop.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface GenericRepository<O, T> extends JpaRepository<O, T>, JpaSpecificationExecutor<O> {
    @Override
    @Query(value = "SELECT e FROM #{#entityName} e WHERE e.delete_at IS NULL", nativeQuery = true)
    Page<O> findAll(Pageable pageable);

    @Override
    @Query(value = "SELECT e FROM #{#entityName} e WHERE e.delete_at IS NULL", nativeQuery = true)
    List<O> findAll();

    @Override
    @Query(value = "SELECT count(*) FROM #{#entityName} e WHERE e.delete_at IS NULL", nativeQuery = true)
    long count();

    @Query(value = "SELECT e FROM #{#entityName} e WHERE e.delete_at IS NOT NULL", nativeQuery = true)
    List<O> recycleBin();

    @Query(value = "SELECT count(*) FROM #{#entityName} e WHERE e.delete_at IS NOT NULL", nativeQuery = true)
    long countAll();
}
