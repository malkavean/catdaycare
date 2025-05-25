package com.catdaycare.controller;

import com.catdaycare.model.Estadia;
import com.catdaycare.model.Gato;
import com.catdaycare.repository.EstadiaRepository;
import com.catdaycare.repository.GatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    public String salvarEstadia(@ModelAttribute Estadia estadia, Model model) {

        LocalDate dataEntrada = estadia.getDataEntrada();
        LocalDate dataSaida = estadia.getDataSaida();

        if (dataEntrada == null || dataSaida == null || dataSaida.isBefore(dataEntrada)) {
            model.addAttribute("erro", "Datas inválidas: a data de saída deve ser igual ou posterior à data de entrada.");
            model.addAttribute("gatos", gatoRepository.findAll());
            return "nova-estadia";
        }

        // Verificar limite para cada dia no intervalo [dataEntrada, dataSaida]
        for (LocalDate date = dataEntrada; !date.isAfter(dataSaida); date = date.plusDays(1)) {
            long total = estadiaRepository.countGatosPorData(date);
            if (total >= 3) {
                model.addAttribute("erro", "Limite máximo de gatos atingido (3) para o dia " + date);
                model.addAttribute("gatos", gatoRepository.findAll());
                return "nova-estadia";
            }
        }

        // Calcular quantidade de dias
        long dias = ChronoUnit.DAYS.between(dataEntrada, dataSaida) + 1;
        BigDecimal precoTotal = BigDecimal.valueOf(dias * 50);
        estadia.setPreco(precoTotal);

        // Garantir que gato está setado corretamente
        if (estadia.getGato() != null && estadia.getGato().getId() != null) {
            Gato gato = gatoRepository.findById(estadia.getGato().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Gato inválido"));
            estadia.setGato(gato);
        } else {
            model.addAttribute("erro", "Selecione um gato válido.");
            model.addAttribute("gatos", gatoRepository.findAll());
            return "nova-estadia";
        }

        estadiaRepository.save(estadia);
        return "redirect:/estadias";
    }
}
