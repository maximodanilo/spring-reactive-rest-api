package com.example.soccerapi.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.soccerapi.model.Player;

public interface PlayerRepository extends ReactiveMongoRepository<Player, String>{

}
