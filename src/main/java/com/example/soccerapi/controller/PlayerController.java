package com.example.soccerapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.soccerapi.model.Player;
import com.example.soccerapi.repository.PlayerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/players")
public class PlayerController {

	private PlayerRepository playerRepository;

	public PlayerController(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@GetMapping
	public Flux<Player> getPlayers() {
		return playerRepository.findAll();
	}

	@GetMapping("{id}")
	public Mono<ResponseEntity<Player>> getPlayer(@PathVariable String id) {
		return playerRepository.findById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Player> savePlayer(@RequestBody Player player) {
		return playerRepository.save(player);
	}

	@PutMapping("{id}")
	public Mono<ResponseEntity<Player>> updatePlayer(@PathVariable(value = "id") String id, @RequestBody Player player) {
		return playerRepository.findById(id).flatMap(existingPlayer -> {
			existingPlayer.setName(player.getName());
			existingPlayer.setPosition(player.getPosition());
			existingPlayer.setTransferDate(player.getTransferDate());
			return playerRepository.save(existingPlayer);
		}).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deletePlayer(@PathVariable(value = "id") String id){
        return playerRepository.findById(id)
                .flatMap(existingPlayer ->
                    playerRepository.delete(existingPlayer)
                            .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllPlayers(){
        return playerRepository.deleteAll();
    }

}
