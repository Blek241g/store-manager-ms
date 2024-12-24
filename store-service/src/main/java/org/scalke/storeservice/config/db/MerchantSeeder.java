package org.scalke.storeservice.config.db;


import com.github.javafaker.Faker;
import org.scalke.storeservice.entities.Merchant;
import org.scalke.storeservice.repositories.MerchantRepository;
import org.scalke.storeservice.services.MerchantService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MerchantSeeder {

    @Bean
    CommandLineRunner initDatabase(MerchantRepository merchantRepository, MerchantService merchantService) {
        return args -> {
            Faker faker = new Faker();

            for(int i = 1; i<21; i++){
               merchantRepository.save(
                       Merchant.builder()
                               .lastname(faker.name().lastName())
                               .firstname(faker.name().firstName())
                               .phoneNumber(faker.phoneNumber().phoneNumber())
                               .email(faker.internet().emailAddress())
                               .userId((long) i)
                               .username("username" + i)
                               .build()
               );
            }
        };
    }
}
