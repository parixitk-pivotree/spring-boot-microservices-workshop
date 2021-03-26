package io.javabrains.movieinfoservice.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Movie {
	
	private String movieId;
	private String name;
	private String description;
	
	public Movie() {
		
	}
	public Movie(String movieId, String name, String description) {
		this.movieId = movieId;
		this.name = name;
		this.description = description;
	}

}
