package com.catdaycare.repository;

import com.catdaycare.model.Dono;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonoRepository extends JpaRepository<Dono, Long> {
    // Pode adicionar métodos personalizados aqui se precisar
}
