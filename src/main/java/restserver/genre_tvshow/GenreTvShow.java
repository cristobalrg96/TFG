package restserver.genre_tvshow;

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
import restserver.tvshow.TvShow;

@Entity
public class GenreTvShow {
	
	public Long genreTvShowId;
	public Genre genreTvShowGenre;
	public TvShow genreTvShowTvShow;
	
	public GenreTvShow(Genre genreTvShowGenre, TvShow genreTvShowTvShow) {
		this.genreTvShowGenre = genreTvShowGenre;
		this.genreTvShowTvShow = genreTvShowTvShow;
	}

	public GenreTvShow() {
	}
	
	@SequenceGenerator( // It only takes effect for
			name = "GenreTvShowIdGenerator", // databases providing identifier
			sequenceName = "GenreTvShowIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "GenreTvShowIdGenerator")
	public Long getGenreTvShowId() {
		return genreTvShowId;
	}
	
	public void setGenreTvShowId(Long genreTvShowId) {
		this.genreTvShowId = genreTvShowId;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="genreTvShowGenre")
	public Genre getGenreTvShowGenre() {
		return genreTvShowGenre;
	}
	
	public void setGenreTvShowGenre(Genre genreTvShowGenre) {
		this.genreTvShowGenre = genreTvShowGenre;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="genreTvShowTvShow")
	@JsonIgnore //Usado para evitar bucle infinito al tener la serie las temporadas
	public TvShow getGenreTvShowTvShow() {
		return genreTvShowTvShow;
	}
	
	public void setGenreTvShowTvShow(TvShow genreTvShowTvShow) {
		this.genreTvShowTvShow = genreTvShowTvShow;
	}
	
}
