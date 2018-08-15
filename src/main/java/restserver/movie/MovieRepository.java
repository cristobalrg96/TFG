package restserver.movie;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends CrudRepository<Movie,Long>{
	
	public Iterable<Movie> findByMovieTitleContaining(String movieTitle);

	public Movie findByMovieApiId(Long movieApiId);
	
	@Query("SELECT m.movieTitle FROM Movie m where m.movieTitle LIKE %:movieTitle%")
	public List<String> findMovieTitleByMovieTitleContaining(@Param("movieTitle") String movieTitle);
	
}
