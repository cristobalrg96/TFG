package restserver.movie_user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restserver.movie.Movie;
import restserver.user.User;
import restserver.util.Status;

@Entity
public class MovieUser {

	Long movieUserId;
	User movieUserUser;
	Movie movieUserMovie;
	Status movieUserStatus;
	Date movieUserDate;
	
	public MovieUser(User movieUserUser, Movie movieUserMovie, Status movieUserStatus) {
		this.movieUserUser = movieUserUser;
		this.movieUserMovie = movieUserMovie;
		this.movieUserStatus = movieUserStatus;
		this.movieUserDate = new Date();
	}

	public MovieUser() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "MovieUserIdGenerator", // databases providing identifier
			sequenceName = "MovieUserIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MovieUserIdGenerator")
	public Long getMovieUserId() {
		return movieUserId;
	}

	public void setMovieUserId(Long movieUserId) {
		this.movieUserId = movieUserId;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="movieUserUser")
	public User getMovieUserUser() {
		return movieUserUser;
	}

	public void setMovieUserUser(User movieUserUser) {
		this.movieUserUser = movieUserUser;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="movieUserMovie")
	public Movie getMovieUserMovie() {
		return movieUserMovie;
	}

	public void setMovieUserMovie(Movie movieUserMovie) {
		this.movieUserMovie = movieUserMovie;
	}

	@Enumerated(EnumType.STRING)
	public Status getMovieUserStatus() {
		return movieUserStatus;
	}

	public void setMovieUserStatus(Status movieUserStatus) {
		this.movieUserStatus = movieUserStatus;
	}

	public Date getMovieUserDate() {
		return movieUserDate;
	}

	public void setMovieUserDate(Date movieUserDate) {
		this.movieUserDate = movieUserDate;
	}
	
}
