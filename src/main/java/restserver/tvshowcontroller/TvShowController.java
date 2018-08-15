package restserver.tvshowcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restserver.tvshow.TvShow;
import restserver.tvshowservice.TvShowService;

@CrossOrigin
@RestController
public class TvShowController {
	
	@Autowired
	TvShowService tvShowService;
	
	@RequestMapping("/tvshow/{id}")
	public TvShow tvShowId(@PathVariable(value="id") Long id) {
		return tvShowService.findTvShowById(id);
	}
	
	@RequestMapping("/tvshow")
	public Iterable<TvShow> findTvShowByTitle(@RequestParam("title") String tvShowTitle) {
		return tvShowService.findTvShowByTitle(tvShowTitle);
	}
	
	@RequestMapping(value = "/tvshow", method = RequestMethod.POST)
	public TvShow addTvShow(@RequestBody Long apiId){
		return tvShowService.addUpdateTvShow(apiId);
	}

}
