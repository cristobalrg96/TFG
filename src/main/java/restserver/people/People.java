package restserver.people;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;

import restserver.movie_people.MoviePeople;
import restserver.tvshow_people.TvShowPeople;

@Entity
@BatchSize(size=100)
public class People {
	private Long peopleId;
	private String peopleName;
	private String peoplePicture;
	private String peopleBiography;
	private Long peopleApiId;
	private Date peopleBirthday;
	private Date peopleDeathday;
	private String peoplePlaceOfBirth;
	private List<TvShowPeople> peopleTvShows;
	private List<MoviePeople> peopleMovies;
	
	public People(String peopleName, String peoplePicture, String peopleBiography, Long peopleApiId,
			Date peopleBirthday, Date peopleDeathday, String peoplePlaceOfBirth) {
		this.peopleName = peopleName;
		this.peoplePicture = peoplePicture;
		this.peopleBiography = peopleBiography;
		this.peopleApiId = peopleApiId;
		this.peopleBirthday = peopleBirthday;
		this.peopleDeathday = peopleDeathday;
		this.peoplePlaceOfBirth = peoplePlaceOfBirth;
	}

	public People() {
	}

	
	@SequenceGenerator( // It only takes effect for
	name = "PeopleIdGenerator", // databases providing identifier
	sequenceName = "PeopleIdSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PeopleIdGenerator")
	public Long getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(Long peopleId) {
		this.peopleId = peopleId;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getPeoplePicture() {
		return peoplePicture;
	}

	public void setPeoplePicture(String peoplePicture) {
		this.peoplePicture = peoplePicture;
	}

	public String getPeopleBiography() {
		return peopleBiography;
	}

	public void setPeopleBiography(String peopleBiography) {
		this.peopleBiography = peopleBiography;
	}

	public Long getPeopleApiId() {
		return peopleApiId;
	}

	public void setPeopleApiId(Long peopleApiId) {
		this.peopleApiId = peopleApiId;
	}

	public Date getPeopleBirthday() {
		return peopleBirthday;
	}

	public void setPeopleBirthday(Date peopleBirthday) {
		this.peopleBirthday = peopleBirthday;
	}

	public Date getPeopleDeathday() {
		return peopleDeathday;
	}

	public void setPeopleDeathday(Date peopleDeathday) {
		this.peopleDeathday = peopleDeathday;
	}

	public String getPeoplePlaceOfBirth() {
		return peoplePlaceOfBirth;
	}

	public void setPeoplePlaceOfBirth(String peoplePlaceOfBirth) {
		this.peoplePlaceOfBirth = peoplePlaceOfBirth;
	}

	@OneToMany(mappedBy="tvShowPeoplePeople")
	public List<TvShowPeople> getPeopleTvShows() {
		return peopleTvShows;
	}

	public void setPeopleTvShows(List<TvShowPeople> peopleTvShows) {
		this.peopleTvShows = peopleTvShows;
	}

	@OneToMany(mappedBy="moviePeoplePeople")
	public List<MoviePeople> getPeopleMovies() {
		return peopleMovies;
	}

	public void setPeopleMovies(List<MoviePeople> peopleMovies) {
		this.peopleMovies = peopleMovies;
	}
	
}
