package io.javabrains.ratingdataservice.models;

import lombok.Data;

@Data
public class Rating {
	
	private String movieId;
	private int rating;
	
	public Rating(String movieId, int rating) {
		super();
		this.movieId = movieId;
		this.rating = rating;
	}

}
