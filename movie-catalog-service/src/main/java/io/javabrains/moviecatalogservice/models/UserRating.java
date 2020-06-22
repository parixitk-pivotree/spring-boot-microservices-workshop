package io.javabrains.moviecatalogservice.models;

import java.util.List;

import lombok.Data;

@Data
public class UserRating {

	private String userId;
	private List<Rating> userRating;
	
}
