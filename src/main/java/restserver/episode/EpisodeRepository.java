package restserver.episode;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import restserver.season.Season;

public interface EpisodeRepository extends CrudRepository<Episode,Long> {

	@Query("FROM Episode e WHERE e.episodeNumber=:episodeNumber AND e.episodeSeason=:episodeSeason")
	public Episode findEpisodeByEpisodeNumberAndSeason(@Param("episodeNumber")int episodeNumber, @Param("episodeSeason") Season episodeSeason);

}
