package com.catdaycare.controller;
import com.catdaycare.model.Dono;
import com.catdaycare.repository.DonoRepository;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DonoViewController {

    @Autowired
    private DonoRepository donoRepository;

    @GetMapping("/donos")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dono", new Dono());
        model.addAttribute("donos", donoRepository.findAll());
        return "donos"; // nomes .html em /templates
    }

    @PostMapping("/donos")
    public String salvarDono(@ModelAttribute Dono dono) {
        donoRepository.save(dono);
        return "redirect:/donos";
    }
}
