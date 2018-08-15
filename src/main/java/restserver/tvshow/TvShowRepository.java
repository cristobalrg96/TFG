package restserver.tvshow;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TvShowRepository extends CrudRepository<TvShow, Long>{

	public Iterable<TvShow> findByTvShowTitleContaining(String tvShowTitle);

	public TvShow findByTvShowApiId(Long tvShowApiId);

	@Query("SELECT t.tvShowTitle FROM TvShow t where t.tvShowTitle LIKE %:tvShowTitle%")
	public List<String> findTvShowTitleByTvShowTitleContaining(@Param("tvShowTitle") String tvShowTitle);

}
