package com.example.spring_servise.repository;

import com.example.spring_servise.model.RankedUrl;
import com.example.spring_servise.model.Url;
import org.apache.el.stream.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface URlRepository extends JpaRepository<Url, Integer>
Optional<Url> findById(Integer id);

        Optional<Url> findByOriginal(String original);

        @Query(value = "SELECT * FROM url ORDER BY count DESC ", nativeQuery = true)
        Page<Url> findAll(Pageable pageable);

        @Query(value = "SELECT * FROM url ORDER BY count DESC ", nativeQuery = true)
        List<Url> findAll();}
