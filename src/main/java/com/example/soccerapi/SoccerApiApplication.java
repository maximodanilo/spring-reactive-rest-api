package com.example.soccerapi;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import com.example.soccerapi.model.Player;
import com.example.soccerapi.repository.PlayerRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SoccerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoccerApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, PlayerRepository playerRepository) {
		return args -> {
			Flux<Player> productFlux = Flux.just(new Player(null, "Cristiano Ronaldo", "ST", LocalDateTime.now()),
					new Player(null, "Ronaldinho", "RW", LocalDateTime.now()),
					new Player(null, "Zidane", "CM", LocalDateTime.now()),
					new Player(null, "Xavi", "CM", LocalDateTime.now()),
					new Player(null, "Matuidi", "CDM", LocalDateTime.now()),
					new Player(null, "Van Djck", "CB", LocalDateTime.now()),
					new Player(null, "Rodrigo Caio", "CB", LocalDateTime.now()),
					new Player(null, "Marcelo", "LB", LocalDateTime.now()),
					new Player(null, "Rafinha", "RB", LocalDateTime.now()),
					new Player(null, "Neymar", "LW", LocalDateTime.now())).flatMap(playerRepository::save);

			productFlux.thenMany(playerRepository.findAll()).subscribe(System.out::println);
		};
	}
}
