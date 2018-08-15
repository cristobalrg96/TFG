package restserver.tvshowservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import restserver.episode.Episode;
import restserver.episode.EpisodeRepository;
import restserver.genre.Genre;
import restserver.genre.GenreRepository;
import restserver.genre_tvshow.GenreTvShow;
import restserver.genre_tvshow.GenreTvShowRepository;
import restserver.people.People;
import restserver.people.PeopleRepository;
import restserver.season.Season;
import restserver.season.SeasonRepository;
import restserver.tvshow.TvShow;
import restserver.tvshow.TvShowRepository;
import restserver.tvshow_people.TvShowPeople;
import restserver.tvshow_people.TvShowPeopleRepository;
import restserver.util.Constants;

@Service("tvShowService")
@Transactional
public class TvShowServiceImpl implements TvShowService{

	@Autowired
	private TvShowRepository tvShowRepository;
	
	@Autowired
	private TvShowPeopleRepository tvShowPeopleRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private GenreTvShowRepository genreTvShowRepository;
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	@Autowired
	private SeasonRepository seasonRepository;

	@Autowired
	private EpisodeRepository episodeRepository;
	
	
	private void addEpisode(Season season, int episodeNumber, JSONObject episodeJSON) {
		
		try {
			String episodeName = episodeJSON.getString("name");
			Object pictureObject = episodeJSON.get("still_path");
			String episodePicture = null;
			if (pictureObject != JSONObject.NULL) {
				episodePicture = Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+pictureObject;
			}
			String episodeSummary = episodeJSON.getString("overview");
			Object dateObject = episodeJSON.get("air_date");
			Date episodeReleaseDate=null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			if(dateObject!=JSONObject.NULL) {
				try {
					episodeReleaseDate = format.parse((String) dateObject);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				episodeReleaseDate=null;
			Episode episode = new Episode(episodeNumber, episodeName, season, episodePicture, episodeSummary, episodeReleaseDate, null);
			episodeRepository.save(episode);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void updateEpisode(Episode episode, JSONObject episodeJSON) {
		try {
			episode.setEpisodeName(episodeJSON.getString("name"));
			Object pictureObject = episodeJSON.get("still_path");
			if (pictureObject != JSONObject.NULL) {
				episode.setEpisodePicture(Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+pictureObject);
			}
			episode.setEpisodeSummary(episodeJSON.getString("overview"));
			Object dateObject = episodeJSON.get("air_date");
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			if(dateObject!=JSONObject.NULL) {
				try {
					episode.setEpisodeReleaseDate(format.parse((String) dateObject));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			episodeRepository.save(episode);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private Boolean addSeason(TvShow tvShow, int seasonNumber) {
		
		URL url;
		HttpURLConnection con;
		BufferedReader in;
		String inputLine, seasonPicture, seasonSummary, seasonTrailer;
		StringBuffer response = new StringBuffer();
		JSONObject myResponse=null, episodeJSON;
		int i;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		JSONArray trailers, episodes;
		Object dateObject;
		Date seasonReleaseDate;
		Season season;
		
		try {
			url = new URL(Constants.BASE_URL_API+Constants.BASE_URL_API_TVSHOW+tvShow.getTvShowApiId()+Constants.BASE_URL_API_SEASON+seasonNumber+
					Constants.API_KEY+Constants.LANGUAGE_API+Constants.APPEND_RESPONSE+Constants.APPEND_VIDEOS);
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
				seasonPicture = Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+myResponse.getString("poster_path");
				seasonSummary=myResponse.getString("overview");
				dateObject = myResponse.get("air_date");
				if(dateObject!=JSONObject.NULL) {
					try {
						seasonReleaseDate = format.parse((String) dateObject);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						seasonReleaseDate=null;
					}
				}
				else
					seasonReleaseDate=null;
				trailers = myResponse.getJSONObject("videos").getJSONArray("results");
				if (trailers.length()!=0)
					seasonTrailer=trailers.getJSONObject(0).getString("key");
				else
					seasonTrailer=null;
				season = new Season(seasonNumber,tvShow,seasonPicture,seasonSummary,seasonReleaseDate,seasonTrailer);
				seasonRepository.save(season);
				episodes=myResponse.getJSONArray("episodes");
				for (i=0;i<episodes.length();i++) {
					episodeJSON = episodes.getJSONObject(i);
					int episodeNumber = episodeJSON.getInt("episode_number");
					addEpisode(season,episodeNumber,episodeJSON);
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) { //No existe la temporada para la serie
			e.printStackTrace();
			return false;
		}	
	
		return true;
	}
	
	
	
	
	
	private void updateSeason(Season season) {
		
		URL url;
		HttpURLConnection con;
		BufferedReader in;
		String inputLine;
		StringBuffer response = new StringBuffer();
		JSONObject myResponse=null, episodeJSON;
		int i, episodeNumber;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		JSONArray trailers, episodes;
		Object dateObject;
		Episode episode;
		
		try {
			url = new URL(Constants.BASE_URL_API+Constants.BASE_URL_API_TVSHOW+season.getSeasonTvShow().getTvShowApiId()+Constants.BASE_URL_API_SEASON+
					season.getSeasonNumber()+Constants.API_KEY+Constants.LANGUAGE_API+Constants.APPEND_RESPONSE+Constants.APPEND_VIDEOS);
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
				season.setSeasonPicture(Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+myResponse.getString("poster_path"));
				season.setSeasonSummary(myResponse.getString("overview"));
				dateObject = myResponse.get("air_date");
				if(dateObject!=JSONObject.NULL) {
					try {
						season.setSeasonReleaseDate(format.parse((String) dateObject));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				trailers = myResponse.getJSONObject("videos").getJSONArray("results");
				if (trailers.length()!=0)
					season.setSeasonTrailer(trailers.getJSONObject(0).getString("key"));
				seasonRepository.save(season);

				episodes=myResponse.getJSONArray("episodes");
				for (i=0;i<episodes.length();i++) {
					
					episodeJSON = episodes.getJSONObject(i);
					
					try {
						episodeNumber=episodeJSON.getInt("episode_number");
					} catch (JSONException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						episodeNumber=-1;
					}
					
					episodeJSON = episodes.getJSONObject(i);
					episode = episodeRepository.findEpisodeByEpisodeNumberAndSeason(episodeNumber, season);
					
					if (episode!=null)
						updateEpisode(episode, episodeJSON);
					else
						addEpisode(season, episodeNumber, episodeJSON);
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) { //No existe la temporada para la serie
			e.printStackTrace();
		}	
	}
	
	
	
	private void addGenres(TvShow tvShow, JSONArray genres) {
		
		Long genreApiId=null;
		Genre genre;
		GenreTvShow genreTvShow;
		
		for (int i=0;i<genres.length();i++) {
			try {
				genreApiId = genres.getJSONObject(i).getLong("id");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			genre = genreRepository.findGenreByGenreApiId(genreApiId);
			genreTvShow = new GenreTvShow(genre, tvShow);
			genreTvShowRepository.save(genreTvShow);
		}
		
	}
	
	private void addPerson(Long peopleApiId, JSONObject peopleObject, Boolean characterBoolean, TvShow tvShow) {
		String inputLine, peoplePicture, peopleBiography, peoplePlaceOfBirth, characterString=null, job;
		Date peopleBirthday, peopleDeathday;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		
		try {
			URL url = new URL(Constants.BASE_URL_API+Constants.BASE_URL_API_PEOPLE+peopleApiId+Constants.API_KEY+Constants.LANGUAGE_API);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		    in.close();
		    try {
		    	JSONObject myResponse = new JSONObject(response.toString());
				String peopleName = myResponse.getString("name");
				Object pictureObject=myResponse.get("profile_path");
				if (pictureObject != JSONObject.NULL) {
					peoplePicture = Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+pictureObject;
				}
				else
					peoplePicture=null;
				peopleBiography = myResponse.getString("biography");
				Object dateObject = myResponse.get("birthday");
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
				Object placeOfBirthObject=myResponse.get("place_of_birth");
				if (placeOfBirthObject != JSONObject.NULL)
					peoplePlaceOfBirth=(String) placeOfBirthObject;
				else
					peoplePlaceOfBirth=null;
				People people = new People(peopleName,peoplePicture,peopleBiography,peopleApiId,peopleBirthday,peopleDeathday,peoplePlaceOfBirth);
				peopleRepository.save(people);
				if (characterBoolean) {
					characterString = peopleObject.getString("character");
					job = "Actor";
				}
				else
					job = peopleObject.getString("job");
				
				
				TvShowPeople tvShowPeople = new TvShowPeople(tvShow,people,job,characterString);
				tvShowPeopleRepository.save(tvShowPeople);
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void updatePerson(People people, JSONObject peopleObject, Boolean characterBoolean, TvShow tvShow) {
		String inputLine, characterString=null, job;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		
		try {
			URL url = new URL(Constants.BASE_URL_API+Constants.BASE_URL_API_PEOPLE+people.getPeopleApiId()+Constants.API_KEY+Constants.LANGUAGE_API);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		    in.close();
		    try {
		    	JSONObject myResponse = new JSONObject(response.toString());
				Object pictureObject=myResponse.get("profile_path");
				if (pictureObject != JSONObject.NULL) {
					people.setPeoplePicture(Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+pictureObject);
				}
				people.setPeopleBiography(myResponse.getString("biography"));
				Object dateObject = myResponse.get("birthday");
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
				Object placeOfBirthObject=myResponse.get("place_of_birth");
				if (placeOfBirthObject != JSONObject.NULL)
					people.setPeoplePlaceOfBirth((String) placeOfBirthObject);
				peopleRepository.save(people);
				
				if (characterBoolean) {
					characterString = peopleObject.getString("character");
					job = "Actor";
				}
				else
					job = peopleObject.getString("job");
				
				TvShowPeople tvShowPeople= tvShowPeopleRepository.findTvShowPeopleByTvShowAndPeopleAndJob(tvShow, people, job);
				if (tvShowPeople == null)
					tvShowPeople = new TvShowPeople(tvShow,people,job,characterString);
				else
					tvShowPeople.setTvShowPeopleCharacter(characterString);
				tvShowPeopleRepository.save(tvShowPeople);
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void addPeople(TvShow tvShow, JSONObject credits) {
		try {
			JSONArray crew = credits.getJSONArray("crew");
			JSONArray cast = credits.getJSONArray("cast");
			Long peopleApiId;
			JSONObject peopleObject;
			People people;
			for (int i=0;i<crew.length();i++) {
				peopleObject=crew.getJSONObject(i);
				peopleApiId = peopleObject.getLong("id");
				people = peopleRepository.findPeopleByPeopleApiId(peopleApiId);
				if (people==null) 
					addPerson(peopleApiId, peopleObject, false, tvShow);
				else
					updatePerson(people, peopleObject, false, tvShow);
			}
			for (int j=0;j<cast.length();j++) {
				peopleObject=cast.getJSONObject(j);
				peopleApiId = peopleObject.getLong("id");
				people = peopleRepository.findPeopleByPeopleApiId(peopleApiId);
				if (people==null) 
					addPerson(peopleApiId, peopleObject, true, tvShow);
				else
					updatePerson(people, peopleObject, true, tvShow);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	 
	private void addSeasons(TvShow tvShow){
		Boolean moreSeasons=true;
		int seasonNumber=0;
		while (moreSeasons||(seasonNumber<2)) {
			moreSeasons=addSeason(tvShow, seasonNumber);
			seasonNumber++;
		}
	}
	
	
	
	private void updateSeasons(TvShow tvShow, JSONArray seasons) {
		
		int seasonNumber;
		Season season;
		
		for (int i=0;i<seasons.length();i++) {
			try {
				seasonNumber=seasons.getJSONObject(i).getInt("season_number");
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				seasonNumber=-1;
			}
			season = seasonRepository.findSeasonBySeasonNumberAndTvShow(seasonNumber, tvShow);	
			if (season!=null)
				updateSeason(season);
			else
				addSeason(tvShow,seasonNumber);
		}
		
	}
	
	
	
	
	@Override
	@Transactional(readOnly = true)
	public TvShow findTvShowById(Long tvShowId) {
		return tvShowRepository.findOne(tvShowId);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<TvShow> findTvShowByTitle(String tvShowTitle) {
		System.out.println(tvShowTitle);
		return tvShowRepository.findByTvShowTitleContaining(tvShowTitle);
	}
	
	@Override
	public TvShow addUpdateTvShow(Long tvShowApiId) {
		TvShow tvShow = tvShowRepository.findByTvShowApiId(tvShowApiId);
		if (tvShow!=null)
			return updateTvShow(tvShow);
		return addTvShow(tvShowApiId);
	}
	
	@Override
	public TvShow addTvShow(Long tvShowApiId) {
		URL url;
		try {
			url = new URL(Constants.BASE_URL_API+Constants.BASE_URL_API_TVSHOW+tvShowApiId+Constants.API_KEY+Constants.LANGUAGE_API
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
				String tvShowTitle = myResponse.getString("name");
				String tvShowPicture = Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+myResponse.getString("poster_path");
				String tvShowTrailer;
				JSONArray trailers = myResponse.getJSONObject("videos").getJSONArray("results");
				if (trailers.length()!=0)
					tvShowTrailer=trailers.getJSONObject(0).getString("key");
				else
					tvShowTrailer=null;
				int tvShowDuration = myResponse.getJSONArray("episode_run_time").getInt(0);
				String tvShowSummary = myResponse.getString("overview");
				
				String dateString = myResponse.getString("first_air_date");
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date tvShowStartDate = null;
				try {
					tvShowStartDate = format.parse(dateString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String tvShowStatus = myResponse.getString("status");
				
				TvShow tvShow = new TvShow(tvShowTitle,tvShowPicture,tvShowTrailer,tvShowDuration,tvShowSummary,tvShowStartDate,tvShowApiId,tvShowStatus);
				tvShowRepository.save(tvShow);
				JSONArray genres = myResponse.getJSONArray("genres");
				addGenres(tvShow,genres);
				JSONObject credits = myResponse.getJSONObject("credits");
				addPeople(tvShow,credits);
				addSeasons(tvShow);
				return tvShow;
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
	public TvShow updateTvShow(TvShow tvShow) {
		URL url;
		try {
			url = new URL(Constants.BASE_URL_API+Constants.BASE_URL_API_TVSHOW+tvShow.getTvShowApiId()+Constants.API_KEY+Constants.LANGUAGE_API
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
				tvShow.setTvShowPicture(Constants.IMAGE_URL_API+Constants.SIZE_IMAGE_API+myResponse.getString("poster_path"));

				JSONArray trailers = myResponse.getJSONObject("videos").getJSONArray("results");
				if (trailers.length()!=0)
					tvShow.setTvShowTrailer(trailers.getJSONObject(0).getString("key"));

				tvShow.setTvShowDuration(myResponse.getJSONArray("episode_run_time").getInt(0));
				tvShow.setTvShowSummary(myResponse.getString("overview"));
				
				String dateString = myResponse.getString("first_air_date");
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				try {
					tvShow.setTvShowStartDate(format.parse(dateString));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				tvShow.setTvShowStatus(myResponse.getString("status"));
				
				tvShowRepository.save(tvShow);

				JSONObject credits = myResponse.getJSONObject("credits");
				addPeople(tvShow,credits);
				JSONArray seasons = myResponse.getJSONArray("seasons");
				updateSeasons(tvShow, seasons);
				return tvShow;
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
