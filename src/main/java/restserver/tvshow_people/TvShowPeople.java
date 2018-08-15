package restserver.tvshow_people;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import restserver.people.People;
import restserver.tvshow.TvShow;

@Entity
public class TvShowPeople {
	
	Long tvShowPeopleId;
	TvShow tvShowPeopleTvShow;
	People tvShowPeoplePeople;
	String tvShowPeopleJob;
	String tvShowPeopleCharacter;
	
	public TvShowPeople(TvShow tvShowPeopleTvShow, People tvShowPeoplePeople, String tvShowPeopleJob, String tvShowPeopleCharacter) {
		this.tvShowPeopleTvShow = tvShowPeopleTvShow;
		this.tvShowPeoplePeople = tvShowPeoplePeople;
		this.tvShowPeopleJob = tvShowPeopleJob;
		this.tvShowPeopleCharacter = tvShowPeopleCharacter;
	}
	
	public TvShowPeople() {
	}

	@SequenceGenerator( // It only takes effect for
	name = "TvShowPeopleIdGenerator", // databases providing identifier
	sequenceName = "TvShowPeopleIdSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TvShowPeopleIdGenerator")
	public Long getTvShowPeopleId() {
		return tvShowPeopleId;
	}

	public void setTvShowPeopleId(Long tvShowPeopleId) {
		this.tvShowPeopleId = tvShowPeopleId;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="tvShowPeopleTvShow")
	@JsonIgnore //Usado para evitar bucle infinito al tener la serie las temporadas
	public TvShow getTvShowPeopleTvShow() {
		return tvShowPeopleTvShow;
	}

	public void setTvShowPeopleTvShow(TvShow tvShowPeopleTvShow) {
		this.tvShowPeopleTvShow = tvShowPeopleTvShow;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="tvShowPeoplePeople")
	@JsonIgnore //Usado para evitar bucle infinito al tener la serie las temporadas
	public People getTvShowPeoplePeople() {
		return tvShowPeoplePeople;
	}

	public void setTvShowPeoplePeople(People tvShowPeoplePeople) {
		this.tvShowPeoplePeople = tvShowPeoplePeople;
	}

	public String getTvShowPeopleJob() {
		return tvShowPeopleJob;
	}

	public void setTvShowPeopleJob(String tvShowPeopleJob) {
		this.tvShowPeopleJob = tvShowPeopleJob;
	}

	public String getTvShowPeopleCharacter() {
		return tvShowPeopleCharacter;
	}

	public void setTvShowPeopleCharacter(String tvShowPeopleCharacter) {
		this.tvShowPeopleCharacter = tvShowPeopleCharacter;
	}

}