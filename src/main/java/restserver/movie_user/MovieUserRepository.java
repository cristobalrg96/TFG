package restserver.movie_user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import restserver.movie.Movie;
import restserver.user.User;

public interface MovieUserRepository extends CrudRepository<MovieUser, Long>{

	@Query("FROM MovieUser m WHERE m.movieUserMovie =:movieUserMovie AND m.movieUserUser =:movieUserUser")
	public MovieUser findMovieUserByMovieAndUser(@Param("movieUserMovie") Movie movieUserMovie, @Param("movieUserUser") User movieUserUser);
	
	
}