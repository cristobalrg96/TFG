package restserver.genre_movie;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import restserver.genre.Genre;
import restserver.movie.Movie;

@Entity
public class GenreMovie {

	public Long genreMovieId;
	public Genre genreMovieGenre;
	public Movie genreMovieMovie;
	
	public GenreMovie(Genre genreMovieGenre, Movie genreMovieMovie) {
		this.genreMovieGenre = genreMovieGenre;
		this.genreMovieMovie = genreMovieMovie;
	}

	public GenreMovie() {
	}
	
	@SequenceGenerator( // It only takes effect for
			name = "GenreMovieIdGenerator", // databases providing identifier
			sequenceName = "GenreMovieIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "GenreMovieIdGenerator")
	public Long getGenreMovieId() {
		return genreMovieId;
	}
	
	public void setGenreMovieId(Long genreMovieId) {
		this.genreMovieId = genreMovieId;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="genreMovieGenre")
	public Genre getGenreMovieGenre() {
		return genreMovieGenre;
	}
	
	public void setGenreMovieGenre(Genre genreMovieGenre) {
		this.genreMovieGenre = genreMovieGenre;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="genreMovieMovie")
	@JsonIgnore //Usado para evitar bucle infinito al tener la serie las temporadas
	public Movie getGenreMovieMovie() {
		return genreMovieMovie;
	}
	
	public void setGenreMovieMovie(Movie genreMovieMovie) {
		this.genreMovieMovie = genreMovieMovie;
	}
	
}
