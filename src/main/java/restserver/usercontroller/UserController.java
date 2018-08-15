package restserver.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restserver.episode_user.EpisodeUser;
import restserver.exceptions.DuplicatedUserEmail;
import restserver.exceptions.DuplicatedUserLogin;
import restserver.exceptions.IncorrectPasswordException;
import restserver.exceptions.UserNotFoundException;
import restserver.movie_user.MovieUser;
import restserver.people_user.PeopleUser;
import restserver.tvshow_user.TvShowUser;
import restserver.user.User;
import restserver.userservice.UserService;
import restserver.util.UserContentStatusDto;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/user/{id}")
	public User findUserById(@PathVariable(value="id") Long id) {
		return userService.findUserById(id);
	}
	
	@RequestMapping("/user")
	public Iterable<User> findUserByLogin(@RequestParam("login") String userLogin) {
		return userService.findUserByUserLogin(userLogin);
	}
	
	@RequestMapping("/login")
	public User login(@RequestParam("username") String userName,@RequestParam("password") String password) throws UserNotFoundException, IncorrectPasswordException{
		return userService.login(userName,password);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User register(@RequestBody User user) throws DuplicatedUserLogin, DuplicatedUserEmail{
		return userService.register(user);
	}
	
	@RequestMapping(value = "/usertvshow", method = RequestMethod.POST)
	public TvShowUser relationTvShowUser(@RequestBody UserContentStatusDto userContentStatusDto){
		return userService.relationTvShowUser(userContentStatusDto);
	}
	
	@RequestMapping(value = "/userepisode", method = RequestMethod.POST)
	public EpisodeUser relationEpisodeUser(@RequestBody UserContentStatusDto userContentStatusDto){
		return userService.relationEpisodeUser(userContentStatusDto);
	}
	
	@RequestMapping(value = "/usermovie", method = RequestMethod.POST)
	public MovieUser relationMovieUser(@RequestBody UserContentStatusDto userContentStatusDto){
		return userService.relationMovieUser(userContentStatusDto);
	}
	
	@RequestMapping(value = "/userpeople", method = RequestMethod.POST)
	public PeopleUser relationPeopleUser(@RequestBody UserContentStatusDto userContentStatusDto){
		return userService.relationPeopleUser(userContentStatusDto);
	}
	
}
