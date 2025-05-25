package com.catdaycare.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
    public class CalendarioViewController {

        @GetMapping("/calendario")
        public String mostrarCalendario() {
            return "calendario"; // sem .html, pois o Thymeleaf vai procurar em /templates
        }
    }
