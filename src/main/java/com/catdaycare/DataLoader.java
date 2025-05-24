package com.catdaycare;

import com.catdaycare.model.Dono;
import com.catdaycare.model.Gato;
import com.catdaycare.repository.DonoRepository;
import com.catdaycare.repository.GatoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(DonoRepository donoRepository, GatoRepository gatoRepository) {
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
            }
        };
    }
}
