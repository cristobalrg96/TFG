package restserver.genre;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends CrudRepository<Genre,Long> {

	@Query("FROM Genre g WHERE g.genreApiId=:genreApiId")
	public Genre findGenreByGenreApiId(@Param("genreApiId") Long genreApiId);
	
}
