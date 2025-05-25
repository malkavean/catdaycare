package com.catdaycare.controller;

import com.catdaycare.model.Estadia;
import com.catdaycare.model.Gato;
import com.catdaycare.repository.EstadiaRepository;
import com.catdaycare.repository.GatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estadias")
public class EstadiaViewController {

    @Autowired
    private EstadiaRepository estadiaRepository;

    @Autowired
    private GatoRepository gatoRepository;

    @GetMapping
    public String listarEstadias(Model model) {
        List<Estadia> estadias = estadiaRepository.findAll();
        estadias.forEach(e -> System.out.println(e + " gato: " + e.getGato()));

        model.addAttribute("estadias", estadias);
        return "estadias";
    }

    @GetMapping("/nova")
    public String novaEstadiaForm(Model model) {
        model.addAttribute("estadia", new Estadia());
        model.addAttribute("gatos", gatoRepository.findAll());
        return "nova-estadia";
    }

    @PostMapping
    public String salvarEstadia(@ModelAttribute Estadia estadia, @RequestParam("gato.id") Long gatoId) {
        Gato gato = gatoRepository.findById(gatoId).orElseThrow(() -> new IllegalArgumentException("Gato invalido"));
        estadia.setGato(gato);
        estadiaRepository.save(estadia);
        return "redirect:/estadias";
    }
}
