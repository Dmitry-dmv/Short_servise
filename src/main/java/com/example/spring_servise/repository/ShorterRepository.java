package com.example.spring_servise.repository;

import com.example.spring_servise.entity.Shorter;
import org.springframework.data.repository.CrudRepository;

public interface ShorterRepository  extends CrudRepository<Shorter, Long> {
    Shorter findByHash(String hash);


    }
