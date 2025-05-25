package com.catdaycare;
import com.catdaycare.model.Dono;
import com.catdaycare.model.Gato;
import com.catdaycare.model.Estadia;
import com.catdaycare.repository.DonoRepository;
import com.catdaycare.repository.GatoRepository;
import com.catdaycare.repository.EstadiaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataPGLoader {

    @Bean
    CommandLineRunner initDatabase(DonoRepository donoRepo, GatoRepository gatoRepo, EstadiaRepository estadiaRepo) {
        return args -> {
            if (donoRepo.count() == 0 && gatoRepo.count() == 0 && estadiaRepo.count() == 0) {

                // Criando Donos
                Dono dono1 = new Dono();
                dono1.setNome("Maria");

                Dono dono2 = new Dono();
                dono2.setNome("Carlos");

                donoRepo.save(dono1);
                donoRepo.save(dono2);

                // Criando Gatos
                Gato gato1 = new Gato();
                gato1.setNome("Mingau");
                gato1.setDono(dono1);

                Gato gato2 = new Gato();
                gato2.setNome("Frajola");
                gato2.setDono(dono2);

                Gato gato3 = new Gato();
                gato3.setNome("Luna");
                gato3.setDono(dono1);

                gatoRepo.save(gato1);
                gatoRepo.save(gato2);
                gatoRepo.save(gato3);

                // Criando Estadias
                Estadia estadia1 = new Estadia();
                estadia1.setGato(gato1);
                estadia1.setDataEntrada(LocalDate.now().minusDays(2));
                estadia1.setDataSaida(LocalDate.now());
                estadia1.setPreco(BigDecimal.valueOf(150));

                Estadia estadia2 = new Estadia();
                estadia2.setGato(gato2);
                estadia2.setDataEntrada(LocalDate.now().minusDays(1));
                estadia2.setDataSaida(LocalDate.now());
                estadia2.setPreco(BigDecimal.valueOf(100));

                estadiaRepo.save(estadia1);
                estadiaRepo.save(estadia2);

                System.out.println("💾 Dados iniciais salvos no PostgreSQL.");
            }
        };
    }
}

