package restserver.movieservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restserver.genre.Genre;
import restserver.genre.GenreRepository;
import restserver.genre_movie.GenreMovie;
import restserver.genre_movie.GenreMovieRepository;
import restserver.movie.Movie;
import restserver.movie.MovieRepository;
import restserver.movie_people.MoviePeople;
import restserver.movie_people.MoviePeopleRepository;
import restserver.people.People;
import restserver.people.PeopleRepository;
import restserver.util.Constants;

@Service("movieService")
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	GenreMovieRepository genreMovieRepository;
	
	@Autowired
	PeopleRepository peopleRepository;
	
	@Autowired
	MoviePeopleRepository moviePeopleRepository;
	
	
	private void addGenre(Movie movie, JSONArray genres) {

		Long genreApiId=null;
		Genre genre;
		GenreMovie genreMovie;
		
		for (int i=0;i<genres.length();i++) {
			try {
				genreApiId = genres.getJSONObject(i).getLong("id");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			genre = genreRepository.findGenreByGenreApiId(genreApiId);
			genreMovie = new GenreMovie(genre, movie);
			genreMovieRepository.save(genreMovie);
		}
		
	}
	
	private void addPerson(Long peopleApiId, JSONObject peopleObject, Boolean characterBoolean, Movie movie) {
		JSONObject myResponse;
		Object pictureObject, dateObject, placeOfBirthObject;
		People people;
		MoviePeople moviePeople;
		String job, character=null, inputLine, peopleName, peoplePicture, peopleBiography, peoplePlaceOfBirth;
		URL url;
		HttpURLConnection con;
		BufferedReader in;
		StringBuffer response = new StringBuffer();
		Date peopleBirthday, peopleDeathday;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			url = new URL(Constants.BASE_URL_API+Constants.BASE_URL_API_PEOPLE+peopleApiId+Constants.API_KEY+Constants.LANGUAGE_API);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		    in.close();
		    try {
				myResponse = new JSONObject(response.toString());
				peopleName = myResponse.getString("name");
				pictureObject=myResponse.get("profile_path");
				if (pictureObject != JSONObject.NULL) {
					peoplePicture = Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+pictureObject;
				}
				else
					peoplePicture=null;
				peopleBiography = myResponse.getString("biography");
				dateObject = myResponse.get("birthday");
				if(dateObject!=JSONObject.NULL) {
					try {
						peopleBirthday = format.parse((String) dateObject);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						peopleBirthday=null;
					}
				}
				else
					peopleBirthday=null;
				dateObject = myResponse.get("deathday");
				if(dateObject!=JSONObject.NULL) {
					try {
						peopleDeathday = format.parse((String) dateObject);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						peopleDeathday=null;
					}
				}
				else
					peopleDeathday=null;
				placeOfBirthObject=myResponse.get("place_of_birth");
				if (placeOfBirthObject != JSONObject.NULL)
					peoplePlaceOfBirth=(String) placeOfBirthObject;
				else
					peoplePlaceOfBirth=null;
				people = new People(peopleName,peoplePicture,peopleBiography,peopleApiId,peopleBirthday,peopleDeathday,peoplePlaceOfBirth);
				peopleRepository.save(people);
				
				if (characterBoolean) {
					character = peopleObject.getString("character");
					job="Actor";
				}
				else 
					job =peopleObject.getString("job");
				
				moviePeople = new MoviePeople(movie,people,job,character);
				moviePeopleRepository.save(moviePeople);
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void updatePerson(People people, JSONObject peopleObject, Boolean characterBoolean, Movie movie) {
		JSONObject myResponse;
		Object pictureObject, dateObject, placeOfBirthObject;
		MoviePeople moviePeople;
		String job, character=null, inputLine;
		URL url;
		HttpURLConnection con;
		BufferedReader in;
		StringBuffer response = new StringBuffer();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			url = new URL(Constants.BASE_URL_API+Constants.BASE_URL_API_PEOPLE+people.getPeopleApiId()+Constants.API_KEY+Constants.LANGUAGE_API);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		    in.close();
		    try {
				myResponse = new JSONObject(response.toString());
				pictureObject=myResponse.get("profile_path");
				if (pictureObject != JSONObject.NULL) {
					people.setPeoplePicture(Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+pictureObject);
				}
				people.setPeopleBiography(myResponse.getString("biography"));
				dateObject = myResponse.get("birthday");
				if(dateObject!=JSONObject.NULL) {
					try {
						people.setPeopleBirthday(format.parse((String) dateObject));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				dateObject = myResponse.get("deathday");
				if(dateObject!=JSONObject.NULL) {
					try {
						people.setPeopleDeathday(format.parse((String) dateObject));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				placeOfBirthObject=myResponse.get("place_of_birth");
				if (placeOfBirthObject != JSONObject.NULL)
					people.setPeoplePlaceOfBirth((String) placeOfBirthObject);
				peopleRepository.save(people);
				
				if (characterBoolean) {
					character = peopleObject.getString("character");
					job="Actor";
				}
				else 
					job =peopleObject.getString("job");
				
				
				moviePeople= moviePeopleRepository.findMoviePeopleByMovieAndPeopleAndJob(movie, people, job);
				if (moviePeople == null)
					moviePeople = new MoviePeople(movie,people,job,character);
				else
					moviePeople.setMoviePeopleCharacter(character);
				moviePeopleRepository.save(moviePeople);
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	private void addPeople(Movie movie, JSONObject credits) {

		try {
			JSONArray crew = credits.getJSONArray("crew");
			JSONArray cast = credits.getJSONArray("cast");
			Long peopleApiId;
			JSONObject peopleObject;
			People people;
			int max = crew.length();
			if (max>Constants.MAX_MOVIE_CREW)
				max=Constants.MAX_MOVIE_CREW;
			for (int i=0;i<max;i++) {
				peopleObject=crew.getJSONObject(i);
				peopleApiId = peopleObject.getLong("id");
				people = peopleRepository.findPeopleByPeopleApiId(peopleApiId);
				if (people==null)
					addPerson(peopleApiId, peopleObject, false, movie);
				else
					updatePerson(people, peopleObject, false, movie);
			}
			max = cast.length();
			if (max>Constants.MAX_MOVIE_CAST)
				max=Constants.MAX_MOVIE_CAST;
			for (int j=0;j<max;j++) {
				peopleObject=cast.getJSONObject(j);
				peopleApiId = peopleObject.getLong("id");
				people = peopleRepository.findPeopleByPeopleApiId(peopleApiId);
				if (people==null)
					addPerson(peopleApiId, peopleObject, true, movie);
				else
					updatePerson(people, peopleObject, true, movie);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Movie findMovieById(Long movieId) {
		return movieRepository.findOne(movieId);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Movie> findMovieByTitle(String movieTitle) {
		return movieRepository.findByMovieTitleContaining(movieTitle);
	}
	
	
	@Override
	public Movie addUpdateMovie(Long movieApiId) {
		Movie movie = movieRepository.findByMovieApiId(movieApiId);
		if (movie!=null)
			return updateMovie(movie);
		return addMovie(movieApiId);
	}
	
	
	@Override
	public Movie addMovie(Long movieApiId){
		try {
			URL url = new URL(Constants.BASE_URL_API+Constants.BASE_URL_API_MOVIE+movieApiId+Constants.API_KEY+Constants.LANGUAGE_API
					+Constants.APPEND_RESPONSE+Constants.APPEND_VIDEOS+"%2C"+Constants.APPEND_CREDITS);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    String inputLine;
		    StringBuffer response = new StringBuffer();
		    while ((inputLine = in.readLine()) != null) {
		     	response.append(inputLine);
		    }
		    in.close();
		    JSONObject myResponse;
			try {
				myResponse = new JSONObject(response.toString());
				String movieTitle = myResponse.getString("original_title");
				String moviePicture = Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+myResponse.getString("poster_path");
				
				String movieTrailer=null;
				JSONArray trailers = myResponse.getJSONObject("videos").getJSONArray("results");
				if (trailers.length()!=0)
					movieTrailer=trailers.getJSONObject(0).getString("key");
				
				int movieDuration = myResponse.getInt("runtime");
				String movieSummary = myResponse.getString("overview");
				
				String dateString = myResponse.getString("release_date");
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date movieReleaseDate = null;
				try {
					movieReleaseDate = format.parse(dateString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String movieStatus = myResponse.getString("status");
				BigDecimal movieCost = BigDecimal.valueOf(myResponse.getDouble("budget"));
				
				Movie movie = new Movie(movieTitle, moviePicture, movieTrailer, movieDuration, 
						movieSummary, movieReleaseDate, movieApiId, movieStatus, movieCost);
				movieRepository.save(movie);
				
				JSONArray genres = myResponse.getJSONArray("genres");
				addGenre(movie,genres);
				JSONObject credits = myResponse.getJSONObject("credits");
				addPeople(movie,credits);
				return movie;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}

	
	@Override
	public Movie updateMovie(Movie movie) {
		try {
			URL url = new URL(Constants.BASE_URL_API+Constants.BASE_URL_API_MOVIE+movie.getMovieApiId()+Constants.API_KEY+Constants.LANGUAGE_API
					+Constants.APPEND_RESPONSE+Constants.APPEND_VIDEOS+"%2C"+Constants.APPEND_CREDITS);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		    String inputLine;
		    StringBuffer response = new StringBuffer();
		    while ((inputLine = in.readLine()) != null) {
		     	response.append(inputLine);
		    }
		    in.close();
		    JSONObject myResponse;
			try {
				myResponse = new JSONObject(response.toString());
				movie.setMoviePicture(Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+myResponse.getString("poster_path"));
				
				JSONArray trailers = myResponse.getJSONObject("videos").getJSONArray("results");
				if (trailers.length()!=0)
					movie.setMovieTrailer(trailers.getJSONObject(0).getString("key"));
				
				movie.setMovieDuration(myResponse.getInt("runtime"));
				movie.setMovieSummary(myResponse.getString("overview"));
				
				String dateString = myResponse.getString("release_date");
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				try {
					movie.setMovieReleaseDate(format.parse(dateString));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				movie.setMovieStatus(myResponse.getString("status"));
				movie.setMovieCost(BigDecimal.valueOf(myResponse.getDouble("budget")));
				
				movieRepository.save(movie);
				
				JSONObject credits = myResponse.getJSONObject("credits");
				addPeople(movie,credits);
				return movie;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	
}
