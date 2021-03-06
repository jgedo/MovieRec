package com.movierec.MovieRec;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
	
	@Id
	private String id;
	
	private String name;
	private String director;
	private String year;
	
}
