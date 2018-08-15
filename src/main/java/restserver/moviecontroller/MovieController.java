package restserver.moviecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restserver.movie.Movie;
import restserver.movieservice.MovieService;

@RestController
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@RequestMapping("/movie/{id}")
	public Movie findMovieById(@PathVariable(value="id") Long id) {
		return movieService.findMovieById(id);
	}
	
	////
	@RequestMapping("/movie")
	public Iterable<Movie> findMovieByTitle(@RequestParam("title") String movieTitle) {
		return movieService.findMovieByTitle(movieTitle);
	}
	
	@RequestMapping(value = "/movie", method = RequestMethod.POST)
	public Movie addMovie(@RequestBody Long apiId){
		return movieService.addUpdateMovie(apiId);
	}

}
