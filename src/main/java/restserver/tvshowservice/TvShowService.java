package restserver.tvshowservice;

import restserver.tvshow.TvShow;

public interface TvShowService {

	TvShow findTvShowById(Long tvShowId);

	Iterable<TvShow> findTvShowByTitle(String tvShowTitle);
	
	TvShow addUpdateTvShow(Long tvShowApiId);
	
	TvShow addTvShow(Long tvShowApiId);
	
	TvShow updateTvShow(TvShow tvShow);

}
