package com.catdaycare.Api;

import com.catdaycare.model.Estadia;
import com.catdaycare.repository.EstadiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadias")
public class EstadiaRestController {

    @Autowired
    private EstadiaRepository estadiaRepository;

    @GetMapping
    public List<Estadia> listarEstadias() {
        return estadiaRepository.findAll();
    }
}
