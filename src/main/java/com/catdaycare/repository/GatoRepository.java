package com.catdaycare.repository;

import com.catdaycare.model.Gato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatoRepository extends JpaRepository<Gato, Long> {
    // Métodos personalizados, se quiser
}
