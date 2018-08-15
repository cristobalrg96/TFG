package restserver.season;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import restserver.tvshow.TvShow;

public interface SeasonRepository extends CrudRepository<Season,Long>{

	@Query("FROM Season s WHERE s.seasonNumber=:seasonNumber AND s.seasonTvShow=:seasonTvShow")
	public Season findSeasonBySeasonNumberAndTvShow(@Param("seasonNumber") int seasonNumber, @Param("seasonTvShow") TvShow seasonTvShow);
	
}
