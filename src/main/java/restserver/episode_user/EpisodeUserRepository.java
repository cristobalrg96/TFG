package restserver.episode_user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import restserver.episode.Episode;
import restserver.user.User;

public interface EpisodeUserRepository extends CrudRepository<EpisodeUser,Long>{

	@Query("FROM EpisodeUser e WHERE e.episodeUserEpisode =:episodeUserEpisode AND e.episodeUserUser =:episodeUserUser")
	public EpisodeUser findEpisodeUserByEpisodeAndUser(@Param("episodeUserEpisode") Episode episodeUserEpisode, @Param("episodeUserUser") User episodeUserUser);

}
