package com.catdaycare.controller;

import com.catdaycare.model.Dono;
import com.catdaycare.repository.DonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donos")
public class DonoController {

    @Autowired
    private DonoRepository donoRepository;

    @GetMapping
    public List<Dono> listarDonos() {
        return donoRepository.findAll();
    }

    @PostMapping
    public Dono criarDono(@RequestBody Dono dono) {
        return donoRepository.save(dono);
    }
}
