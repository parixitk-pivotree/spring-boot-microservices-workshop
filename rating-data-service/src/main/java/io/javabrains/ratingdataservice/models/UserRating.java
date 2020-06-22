package io.javabrains.ratingdataservice.models;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class UserRating {

	private String userId;
	private List<Rating> userRating;
	
	 public void initData(String userId) {
	        this.setUserId(userId);
	        this.setUserRating(Arrays.asList(
	                new Rating("100", 4),
	                new Rating("200", 5)
	        ));
	    }
}
