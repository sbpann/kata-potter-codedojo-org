package org.codingdojo.potterkata;

import org.codingdojo.potterkata.seeds.BookSeed;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PotterKataApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PotterKataApplication.class, args);
    }

    @Override
    public void run(String... args) {
        BookSeed.seed();
    }
}
