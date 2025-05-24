package com.catdaycare.controller;
import org.springframework.ui.Model;
import com.catdaycare.repository.DonoRepository;
import com.catdaycare.repository.GatoRepository;
//import com.catdaycare.model.Dono;
import com.catdaycare.model.Gato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class GatoViewController {

    @Autowired
    private GatoRepository gatoRepository;

    @Autowired
    private DonoRepository donoRepository;

    @GetMapping("/gatos")
    public String mostrarFormulario(Model model) {
        model.addAttribute("gato", new Gato());
        model.addAttribute("gatos", gatoRepository.findAll());
        model.addAttribute("donos", donoRepository.findAll());
        return "gatos"; // nome do HTML que vamos criar
    }
    @GetMapping("/novo")
    public String novoGatoForm(Model model) {
        model.addAttribute("gato", new Gato());
        return "novo-gato";
    }
    @PostMapping("/gatos")
    public String salvarGato(@ModelAttribute Gato gato, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("donos", donoRepository.findAll());
            model.addAttribute("gatos", gatoRepository.findAll());
            return "gatos"; // mostra o formulário novamente com erros
        }

        gatoRepository.save(gato);
        return "redirect:/gatos";
    }
}
