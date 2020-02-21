package com.example.soccerapi.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Player {

	@Id
	private String id;
	private String name;
	private String position;
	private LocalDateTime transferDate;

	public Player() {
	}

	public Player(String id, String name, String position, LocalDateTime transferDate) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.transferDate = transferDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public LocalDateTime getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(LocalDateTime transferDate) {
		this.transferDate = transferDate;
	}

	@Override
	public String toString() {
		return "Player {id=" + id + ", name=" + name + ", position=" + position + ", transferDate=" + transferDate
				+ "}";
	}

}
