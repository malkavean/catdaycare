package com.catdaycare.repository;

import com.catdaycare.model.Estadia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface EstadiaRepository extends JpaRepository<Estadia, Long> {
    @Query("SELECT COUNT(e) FROM Estadia e WHERE e.dataEntrada <= :data AND e.dataSaida >= :data")
    long countGatosPorData(@Param("data") LocalDate data);
}
