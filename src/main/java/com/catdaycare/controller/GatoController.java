package com.catdaycare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.catdaycare.model.Gato;
import com.catdaycare.repository.GatoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/gatos")
public class GatoController {

    @Autowired
    private GatoRepository gatoRepository;

    @GetMapping
    public List<Gato> listarGatos() {
        return gatoRepository.findAll();
    }

    @PostMapping
    public Gato criarGato(@RequestBody Gato gato) {
        return gatoRepository.save(gato);
    }
}
