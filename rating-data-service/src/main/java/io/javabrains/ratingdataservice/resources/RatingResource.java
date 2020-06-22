package io.javabrains.ratingdataservice.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingdataservice.models.Rating;
import io.javabrains.ratingdataservice.models.UserRating;


@RestController
@RequestMapping("/ratings")
public class RatingResource {
	
	@RequestMapping("movies/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId){
		return new Rating(movieId, 3);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId){
		
//		List<Rating> ratings = Arrays.asList(new Rating("100", 4),
//											 new Rating("200", 5));
		
		UserRating userRating = new UserRating();
		userRating.initData(userId);
		return userRating;
	}

}
