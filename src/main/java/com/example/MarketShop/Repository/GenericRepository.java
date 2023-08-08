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
    @Query("SELECT e FROM #{#entityName} e WHERE e.delete_at IS NULL")
    Page<O> findAll(Pageable pageable);

    @Override
    @Query("SELECT e FROM #{#entityName} e WHERE e.delete_at IS NULL")
    List<O> findAll();

    @Override
    @Query("SELECT count(*) FROM #{#entityName} e WHERE e.delete_at IS NULL")
    long count();

    @Query("SELECT e FROM #{#entityName} e WHERE e.delete_at IS NOT NULL")
    List<O> recycleBin();

    @Query("SELECT count(*) FROM #{#entityName} e WHERE e.delete_at IS NOT NULL")
    long countAll();
}
