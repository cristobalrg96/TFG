package restserver.generalservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restserver.movie.MovieRepository;
import restserver.people.PeopleRepository;
import restserver.tvshow.TvShowRepository;
import restserver.user.UserRepository;

@Service("generalService")
@Transactional
public class GeneralServiceImpl implements GeneralService{

	@Autowired
	TvShowRepository tvShowRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	PeopleRepository peopleRepository;
	
	@Autowired
	UserRepository userRepository;
	@Override
	public List<String> findAllByChain(String chain) {
		List<String> results = tvShowRepository.findTvShowTitleByTvShowTitleContaining(chain);
		results.addAll(movieRepository.findMovieTitleByMovieTitleContaining(chain));
		results.addAll(peopleRepository.findPeopleNameByPeopleNameContaining(chain));
		results.addAll(userRepository.findUserLoginByUserLoginContaining(chain));
		return results;
	}

}
