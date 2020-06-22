package io.javabrains.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	//get the list of Movie Ids
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	//@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		//fallback mechanism code
		UserRating ratings = getUserRating(userId);
		return ratings.getUserRating().stream()
				.map(rating -> getCatalogItem(rating))
				.collect(Collectors.toList());
		
		//RestTemplate restTemplate = new RestTemplate();
		//Movie movie = restTemplate.getForObject("http://localhost:8083/movies/foo", Movie.class);
		
		//WebClient.Builder builder = WebClient.builder();
		
//		UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratings/users/" + userId, UserRating.class);
//			
//		return ratings.getUserRating().stream().map( rating -> {
			
			//below line making API call and doing un-marshalling of object
			//For each movie id, call the Movie Info service and get details
//			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
//			System.out.println(movie);
			
//		Movie movie = webClientBuilder.build()
//			.get()
//			.uri("http://movie-info-service/movies/" + rating.getMovieId())
//			.retrieve()
//			.bodyToMono(Movie.class)
//			.block();
			
			// Put them altogether in one place
//			return new CatalogItem(movie.getName() ,movie.getDescription(), rating.getRating());
//		})
//		.collect(Collectors.toList());
		
		/*
		 * CatalogItem catalogItem = new CatalogItem("Dhoom", "Dhoom macha le" , 4);
		 * return Collections.singletonList(catalogItem);
		 */
	}
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	private CatalogItem getCatalogItem(Rating rating){
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName() ,movie.getDescription(), rating.getRating());
	}
	
	private CatalogItem getFallbackCatalogItem(Rating rating){
		return new CatalogItem("no movie name found", "", rating.getRating());
	}
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	private UserRating getUserRating(@PathVariable("userId") String userId){
		return restTemplate.getForObject("http://rating-data-service/ratings/users/" + userId, UserRating.class);
	}
	
	private UserRating getFallbackUserRating(@PathVariable("userId") String userId){
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRating(Arrays.asList(
				new Rating("0", 0)));
		return userRating;
	}
	
	/*
	 * public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String
	 * userId){ return Arrays.asList(new CatalogItem("No Movie", "", 0)); }
	 */
	
}
