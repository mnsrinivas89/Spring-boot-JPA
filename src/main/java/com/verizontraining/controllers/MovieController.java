package com.verizontraining.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.verizontraining.models.Movie;
import com.verizontraining.models.Post;
import com.verizontraining.repositories.MovieRepository;
import com.verizontraining.requestobj.MovieRequest;

@RestController
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/movies")
	private List<Movie> getMovies() {
		return movieRepository.findAll();
	}
	
	@PostMapping("/movie")
	private Movie createMovie(@RequestBody MovieRequest movReq) {
		Movie movie  = new Movie();
		movie.setName(movReq.getName());
		movie.setDirector(movReq.getDirector());
		movie.setHero(movReq.getHero());
		movie.setRating(movReq.getRating());
		
		return movieRepository.save(movie);
		
	}
	
	@PutMapping("/movie/{id}")
	private Movie updateMovie(@RequestBody MovieRequest movReq, @PathVariable("id") Long id) {
		Movie movie  = new Movie();
		movie.setId(id);
		movie.setName(movReq.getName());
		movie.setDirector(movReq.getDirector());
		movie.setHero(movReq.getHero());
		movie.setRating(movReq.getRating());
		return movieRepository.save(movie);
	}
	
	@DeleteMapping("/movie/{id}")
	private void deleteMovie(@PathVariable("id") Long id) {
		movieRepository.deleteById(id);
	}
	
	@GetMapping("/movie/{id}")
	private Optional<Movie> getMovie(@PathVariable("id") Long id) {
		return movieRepository.findById(id);
	}
	
	@GetMapping("/movies/{rating}")
	private List<Movie> getMoviesByRating(@PathVariable("rating") Double rating){
		return movieRepository.findByRatingGreaterThanEqual(rating);
	}
	
	@GetMapping("/movies/hero/{hero}")
	private List<Movie> getMoviesByHero(@PathVariable("hero") String hero){
		return movieRepository.findByHero(hero);
	}
	
	@GetMapping("/get-post")
	private Post getPost() {
		
		
		 ResponseEntity<Post> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1",Post.class);
		 return response.getBody();
	
		//return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1",Post.class);
		//return new Post();
	}
	@GetMapping("/get-posts")
	private Post[] getPosts() {
		
		
		 ResponseEntity<Post[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts",Post[].class);
		 return response.getBody();
	
		//return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1",Post.class);
		//return new Post();
	}
	
	
	
}
