package restserver.userservice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restserver.episode.Episode;
import restserver.episode.EpisodeRepository;
import restserver.episode_user.EpisodeUser;
import restserver.episode_user.EpisodeUserRepository;
import restserver.exceptions.DuplicatedUserEmail;
import restserver.exceptions.DuplicatedUserLogin;
import restserver.exceptions.IncorrectPasswordException;
import restserver.exceptions.UserNotFoundException;
import restserver.movie.Movie;
import restserver.movie.MovieRepository;
import restserver.movie_user.MovieUser;
import restserver.movie_user.MovieUserRepository;
import restserver.people.People;
import restserver.people.PeopleRepository;
import restserver.people_user.PeopleUser;
import restserver.people_user.PeopleUserRepository;
import restserver.tvshow.TvShow;
import restserver.tvshow.TvShowRepository;
import restserver.tvshow_user.TvShowUser;
import restserver.tvshow_user.TvShowUserRepository;
import restserver.user.User;
import restserver.user.UserRepository;
import restserver.util.PasswordEncrypter;
import restserver.util.Status;
import restserver.util.UserContentStatusDto;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TvShowUserRepository tvShowUserRepository;
	
	@Autowired
	TvShowRepository tvShowRepository;
	
	@Autowired
	EpisodeUserRepository episodeUserRepository;
	
	@Autowired
	EpisodeRepository episodeRepository;
	
	@Autowired
	MovieUserRepository movieUserRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	PeopleUserRepository peopleUserRepository;
	
	@Autowired
	PeopleRepository peopleRepository;
	
	@Override
	@Transactional(readOnly = true)
	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findUserByUserLogin(String userLogin) {
		return userRepository.findByUserLoginContaining(userLogin);
	}
	
	@Override
	@Transactional(readOnly = true)
	public User login(String userName, String password) throws UserNotFoundException, IncorrectPasswordException {
		User user = userRepository.findUserByLoginName(userName);
		if (user==null) 
			throw new UserNotFoundException(userName);
		if (!PasswordEncrypter.isClearPasswordCorrect(password,user.getUserPassword()))
			throw new IncorrectPasswordException(userName);
		return user;
	}

	@Override
	public User register(User user) throws DuplicatedUserLogin, DuplicatedUserEmail {
		
		if (userRepository.findUserByEmail(user.getUserEmail())!=null)
			throw new DuplicatedUserEmail(user.getUserEmail());
		if (userRepository.findUserByLogin(user.getUserLogin())!=null)
			throw new DuplicatedUserLogin(user.getUserLogin());
		

		String encryptedPassword=PasswordEncrypter.crypt(user.getUserPassword());
		user.setUserPassword(encryptedPassword);
		user.setUserPrivacy(false);
		user.setUserCreationDate(new Date());
		userRepository.save(user);
		return user;
	}

	@Override
	public TvShowUser relationTvShowUser(UserContentStatusDto userContentStatusDto) {
		User user = userRepository.findOne(userContentStatusDto.getUserId());
		TvShow tvShow = tvShowRepository.findOne(userContentStatusDto.getContentId());
		if ((tvShow==null)||(user==null))
			return null;
		TvShowUser tvShowUser = tvShowUserRepository.findTvShowUserByTvShowAndUser(tvShow, user);
		if (tvShowUser!=null) {
			tvShowUser.setTvShowUserStatus(userContentStatusDto.getStatus());
			tvShowUser.setTvShowUserDate(new Date());
		}
		else 
			tvShowUser = new TvShowUser(user,tvShow,userContentStatusDto.getStatus());
		tvShowUserRepository.save(tvShowUser);
		return tvShowUser;
	}
	
	@Override
	public EpisodeUser relationEpisodeUser(UserContentStatusDto userContentStatusDto) {
		if ((userContentStatusDto.getStatus().equals(Status.FOLLOWING))||(userContentStatusDto.getStatus().equals(Status.TO_WATCH)))
			return null;
		User user = userRepository.findOne(userContentStatusDto.getUserId());
		Episode episode = episodeRepository.findOne(userContentStatusDto.getContentId());
		if ((episode==null)||(user==null))
			return null;
		EpisodeUser episodeUser = episodeUserRepository.findEpisodeUserByEpisodeAndUser(episode, user);
		if (episodeUser!=null) {
			episodeUser.setEpisodeUserStatus(userContentStatusDto.getStatus());
			episodeUser.setEpisodeUserDate(new Date());
		}
		else 
			episodeUser = new EpisodeUser(user,episode,userContentStatusDto.getStatus());
		episodeUserRepository.save(episodeUser);
		return episodeUser;
	}
	
	@Override
	public MovieUser relationMovieUser(UserContentStatusDto userContentStatusDto) {
		if (userContentStatusDto.getStatus().equals(Status.FOLLOWING))
			return null;
		User user = userRepository.findOne(userContentStatusDto.getUserId());
		Movie movie = movieRepository.findOne(userContentStatusDto.getContentId());
		if ((movie==null)||(user==null))
			return null;
		MovieUser movieUser = movieUserRepository.findMovieUserByMovieAndUser(movie, user);
		if (movieUser!=null) {
			movieUser.setMovieUserStatus(userContentStatusDto.getStatus());
			movieUser.setMovieUserDate(new Date());
		}
		else 
			movieUser = new MovieUser(user,movie,userContentStatusDto.getStatus());
		movieUserRepository.save(movieUser);
		return movieUser;
	}
	
	@Override
	public PeopleUser relationPeopleUser(UserContentStatusDto userContentStatusDto) {
		if ((userContentStatusDto.getStatus().equals(Status.WATCHED))||(userContentStatusDto.getStatus().equals(Status.TO_WATCH)))
			return null;
		User user = userRepository.findOne(userContentStatusDto.getUserId());
		People people = peopleRepository.findOne(userContentStatusDto.getContentId());
		if ((people==null)||(user==null))
			return null;
		PeopleUser peopleUser = peopleUserRepository.findPeopleUserByPeopleAndUser(people, user);
		if (peopleUser!=null) {
			peopleUser.setPeopleUserStatus(userContentStatusDto.getStatus());
			peopleUser.setPeopleUserDate(new Date());
		}
		else 
			peopleUser = new PeopleUser(user,people,userContentStatusDto.getStatus());
		peopleUserRepository.save(peopleUser);
		return peopleUser;
	}
}
