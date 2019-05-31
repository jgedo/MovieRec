package com.movierec.MovieRec;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@Log4j2
@Component
//@org.springframework.context.annotation.Profile("demo")
class SimpleDataInitializer implements ApplicationListener<ApplicationReadyEvent> {

	private final MovieRepository repository;
	
	public SimpleDataInitializer(MovieRepository repo) {
		repository = repo;
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// TODO Auto-generated method stub
		System.out.println("data init");
		Object[][] data = {  
			      {"1", "Matrix", "Andy Wachowski", 1999},  
			      {"2", "Inception", "Christopher Nolan", 2010},  
			      {"3", "Marvel's Avengers", "Joss Whedon", 2012}  
			  };	
		repository
        	.deleteAll() 
        	.thenMany(
        		Flux.just(data)
        			.map(array -> new Movie((String) array[0], (String) array[1], (String) array[2], (String) array[3]))
        			.flatMap(repository::save) 
        	)
        	.thenMany(repository.findAll())
        	.subscribe(movie -> log.info("saving " + movie.toString()));
	}
}
