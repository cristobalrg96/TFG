package restserver.genre;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;

@Entity
@BatchSize(size=100)
public class Genre {
	
	public Long genreId;
	public String genreName;
	public Long genreApiId;
	
	public Genre(String genreName, Long genreApiId) {
		this.genreName = genreName;
		this.genreApiId = genreApiId;
	}

	public Genre() {
	}
	
	@SequenceGenerator( // It only takes effect for
			name = "GenreIdGenerator", // databases providing identifier
			sequenceName = "GenreIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "GenreIdGenerator")
	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public Long getGenreApiId() {
		return genreApiId;
	}

	public void setGenreApiId(Long genreApiId) {
		this.genreApiId = genreApiId;
	}
	
}
