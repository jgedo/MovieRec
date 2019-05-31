package com.movierec.MovieRec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/movies")
public class MovieController {

	private MovieRepository movieRepository;
	
	public MovieController(MovieRepository repo) {
		movieRepository = repo;
	}
	
	@PostMapping()
	public @ResponseBody Mono<Movie> addMovie(@RequestBody Movie movie) {
		return movieRepository.save(movie);
	}
	
	@GetMapping()
	public @ResponseBody Flux<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
}
