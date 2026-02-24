package com.song.demo;

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDataBase {
    @Bean
    CommandLineRunner initDataBase1(SongRepository songRepository) {
        return args -> {
            songRepository.save(
                new Song(
                    "Messages From the Stars",
                    "The RAH Band",
                    "Going Up",
                    LocalDate.of(1983, 1, 1)
                )
            );

            songRepository.save(
                new Song(
                    "My Generation",
                    "The Who",
                    "My Generation",
                    LocalDate.of(1965, 12, 3)
                )
            );
        };
    }
}