package restserver.tvshow_user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import restserver.tvshow.TvShow;
import restserver.user.User;


public interface TvShowUserRepository extends CrudRepository<TvShowUser,Long>{

	@Query("FROM TvShowUser t WHERE t.tvShowUserTvShow =:tvShowUserTvShow AND t.tvShowUserUser =:tvShowUserUser")
	public TvShowUser findTvShowUserByTvShowAndUser(@Param("tvShowUserTvShow") TvShow tvShowUserTvShow, @Param("tvShowUserUser") User tvShowUserUser);
	
}
