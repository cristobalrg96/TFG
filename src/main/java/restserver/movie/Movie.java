package restserver.movie;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;

import restserver.genre_movie.GenreMovie;
import restserver.movie_people.MoviePeople;

@Entity
@BatchSize(size=50)
public class Movie {
	private Long movieId;
	private String movieTitle;
	private String moviePicture;
	private String movieTrailer;
	private int movieDuration;
	private String movieSummary;
	private Date movieReleaseDate;
	private Long movieApiId;
	private String movieStatus;
	private BigDecimal movieCost;
	private List<GenreMovie> movieGenres;
	private List<MoviePeople> movieCredits;

	
	public Movie(String movieTitle, String moviePicture, String movieTrailer, int movieDuration,
			String movieSummary, Date movieReleaseDate, Long movieApiId, String movieStatus, BigDecimal movieCost) {
		this.movieTitle = movieTitle;
		this.moviePicture = moviePicture;
		this.movieTrailer = movieTrailer;
		this.movieDuration = movieDuration;
		this.movieSummary = movieSummary;
		this.movieReleaseDate = movieReleaseDate;
		this.movieApiId = movieApiId;
		this.movieStatus = movieStatus;
		this.setMovieCost(movieCost);
	}
	
	public Movie() {
	}

	@SequenceGenerator( // It only takes effect for
	name = "MovieIdGenerator", // databases providing identifier
	sequenceName = "MovieIdSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MovieIdGenerator")
	public Long getMovieId() {
		return movieId;
	}
	
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	
	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMoviePicture() {
		return moviePicture;
	}

	public void setMoviePicture(String moviePicture) {
		this.moviePicture = moviePicture;
	}

	public String getMovieTrailer() {
		return movieTrailer;
	}

	public void setMovieTrailer(String movieTrailer) {
		this.movieTrailer = movieTrailer;
	}

	public int getMovieDuration() {
		return movieDuration;
	}

	public void setMovieDuration(int movieDuration) {
		this.movieDuration = movieDuration;
	}

	public String getMovieSummary() {
		return movieSummary;
	}

	public void setMovieSummary(String movieSummary) {
		this.movieSummary = movieSummary;
	}

	public Date getMovieReleaseDate() {
		return movieReleaseDate;
	}

	public void setMovieReleaseDate(Date movieReleaseDate) {
		this.movieReleaseDate = movieReleaseDate;
	}

	public Long getMovieApiId() {
		return movieApiId;
	}

	public void setMovieApiId(Long movieApiId) {
		this.movieApiId = movieApiId;
	}

	public String getMovieStatus() {
		return movieStatus;
	}

	public void setMovieStatus(String movieStatus) {
		this.movieStatus = movieStatus;
	}

	public BigDecimal getMovieCost() {
		return movieCost;
	}

	public void setMovieCost(BigDecimal movieCost) {
		this.movieCost = movieCost;
	}

	@OneToMany(mappedBy="genreMovieMovie")
	public List<GenreMovie> getMovieGenres() {
		return movieGenres;
	}

	public void setMovieGenres(List<GenreMovie> movieGenres) {
		this.movieGenres = movieGenres;
	}

	@OneToMany(mappedBy="moviePeopleMovie")
	public List<MoviePeople> getMovieCredits() {
		return movieCredits;
	}

	public void setMovieCredits(List<MoviePeople> movieCredits) {
		this.movieCredits = movieCredits;
	}
}
