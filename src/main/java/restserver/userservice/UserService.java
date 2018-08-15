package restserver.userservice;

import restserver.episode_user.EpisodeUser;
import restserver.exceptions.DuplicatedUserEmail;
import restserver.exceptions.DuplicatedUserLogin;
import restserver.exceptions.IncorrectPasswordException;
import restserver.exceptions.UserNotFoundException;
import restserver.movie_user.MovieUser;
import restserver.people_user.PeopleUser;
import restserver.tvshow_user.TvShowUser;
import restserver.user.User;
import restserver.util.UserContentStatusDto;

public interface UserService {

	User findUserById(Long id);
	
	Iterable<User> findUserByUserLogin(String userLogin);
	
	public User login(String loginName, String password) throws UserNotFoundException, IncorrectPasswordException;
	
	public User register(User user) throws DuplicatedUserLogin, DuplicatedUserEmail;

	TvShowUser relationTvShowUser(UserContentStatusDto userContentStatusDto);

	EpisodeUser relationEpisodeUser(UserContentStatusDto userContentStatusDto);

	MovieUser relationMovieUser(UserContentStatusDto userContentStatusDto);

	PeopleUser relationPeopleUser(UserContentStatusDto userContentStatusDto);

}
