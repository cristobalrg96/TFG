package restserver.movieservice;

import restserver.movie.Movie;

public interface MovieService {

	Movie findMovieById(Long movieId);

	Movie addUpdateMovie(Long movieApiId);
	
	Movie addMovie(Long movieApiId);
	
	Movie updateMovie(Movie movie);
	
	Iterable<Movie> findMovieByTitle(String movieTitle);

}
