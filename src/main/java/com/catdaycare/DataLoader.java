package com.catdaycare;

import com.catdaycare.model.Dono;
import com.catdaycare.model.Estadia;
import com.catdaycare.model.Gato;
import com.catdaycare.repository.DonoRepository;
import com.catdaycare.repository.GatoRepository;
import com.catdaycare.repository.EstadiaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(DonoRepository donoRepository, GatoRepository gatoRepository, EstadiaRepository estadiaRepository) {
        return args -> {
            if (donoRepository.count() == 0) {  // evita duplicar a cada start
                Dono dono1 = new Dono();
                dono1.setNome("Maria");
                donoRepository.save(dono1);

                Dono dono2 = new Dono();
                dono2.setNome("João");
                donoRepository.save(dono2);

                Gato gato1 = new Gato();
                gato1.setNome("Mingau");
                gato1.setDono(dono1);
                gatoRepository.save(gato1);

                Gato gato2 = new Gato();
                gato2.setNome("Bolinha");
                gato2.setDono(dono2);
                gatoRepository.save(gato2);
/*
                // Cria estadias
                Estadia estadia1 = new Estadia();
                estadia1.setGato(gato1);
                estadia1.setDataEntrada(LocalDate.now().plusDays(2));
                estadia1.setDataSaida(LocalDate.now().plusDays(6));

                Estadia estadia2 = new Estadia();
                estadia1.setGato(gato2);
                estadia1.setDataEntrada(LocalDate.now().plusDays(8));
                estadia1.setDataSaida(LocalDate.now().plusDays(10));

                estadiaRepository.save(estadia1);
                estadiaRepository.save(estadia2);
*/
            }
        };
    }
}
