package restserver.movie_people;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import restserver.movie.Movie;
import restserver.people.People;
import restserver.movie_people.MoviePeople;

public interface MoviePeopleRepository extends CrudRepository<MoviePeople,Long>{

	@Query("FROM MoviePeople m WHERE m.moviePeopleMovie=:moviePeopleMovie AND m.moviePeoplePeople=:moviePeoplePeople"
							+ " AND m.moviePeopleJob like :moviePeopleJob")
	public MoviePeople findMoviePeopleByMovieAndPeopleAndJob(@Param("moviePeopleMovie")Movie moviePeopleMovie, 
			@Param("moviePeoplePeople") People moviePeoplePeople, @Param("moviePeopleJob") String moviePeopleJob);

}
