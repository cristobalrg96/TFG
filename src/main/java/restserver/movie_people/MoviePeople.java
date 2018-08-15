package restserver.movie_people;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import restserver.movie.Movie;
import restserver.people.People;

@Entity
public class MoviePeople {
	
	public Long moviePeopleId;
	public Movie moviePeopleMovie;
	public People moviePeoplePeople;
	public String moviePeopleJob;
	public String moviePeopleCharacter;
	
	public MoviePeople(Movie moviePeopleMovie, People moviePeoplePeople, String moviePeopleJob,
			String moviePeopleCharacter) {
		this.moviePeopleMovie = moviePeopleMovie;
		this.moviePeoplePeople = moviePeoplePeople;
		this.moviePeopleJob = moviePeopleJob;
		this.moviePeopleCharacter = moviePeopleCharacter;
	}
	
	public MoviePeople() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "MoviePeopleIdGenerator", // databases providing identifier
			sequenceName = "MoviePeopleIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MoviePeopleIdGenerator")
	public Long getMoviePeopleId() {
		return moviePeopleId;
	}

	public void setMoviePeopleId(Long moviePeopleId) {
		this.moviePeopleId = moviePeopleId;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="moviePeopleMovie")
	@JsonIgnore //Usado para evitar bucle infinito al tener la serie las temporadas
	public Movie getMoviePeopleMovie() {
		return moviePeopleMovie;
	}

	public void setMoviePeopleMovie(Movie moviePeopleMovie) {
		this.moviePeopleMovie = moviePeopleMovie;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="moviePeoplePeople")
	@JsonIgnore //Usado para evitar bucle infinito al tener la serie las temporadas
	public People getMoviePeoplePeople() {
		return moviePeoplePeople;
	}

	public void setMoviePeoplePeople(People moviePeoplePeople) {
		this.moviePeoplePeople = moviePeoplePeople;
	}

	public String getMoviePeopleJob() {
		return moviePeopleJob;
	}

	public void setMoviePeopleJob(String moviePeopleJob) {
		this.moviePeopleJob = moviePeopleJob;
	}

	public String getMoviePeopleCharacter() {
		return moviePeopleCharacter;
	}

	public void setMoviePeopleCharacter(String moviePeopleCharacter) {
		this.moviePeopleCharacter = moviePeopleCharacter;
	}
	
}