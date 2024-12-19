package com.exemplu.repository;

import com.exemplu.entity.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarteRepository extends JpaRepository<Carte, Integer> {
    List<Carte> findByAutorul(String autorul);
}
