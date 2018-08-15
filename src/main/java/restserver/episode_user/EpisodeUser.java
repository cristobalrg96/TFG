package restserver.episode_user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restserver.episode.Episode;
import restserver.user.User;
import restserver.util.Status;

@Entity
public class EpisodeUser {

	Long episodeUserId;
	User episodeUserUser;
	Episode episodeUserEpisode;
	Status episodeUserStatus;
	Date episodeUserDate;
	
	public EpisodeUser(User episodeUserUser, Episode episodeUserEpisode, Status episodeUserStatus) {
		this.episodeUserUser = episodeUserUser;
		this.episodeUserEpisode = episodeUserEpisode;
		this.episodeUserStatus = episodeUserStatus;
		this.episodeUserDate = new Date();
	}

	public EpisodeUser() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "EpisodeUserIdGenerator", // databases providing identifier
			sequenceName = "EpisodeUserIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EpisodeUserIdGenerator")
	public Long getEpisodeUserId() {
		return episodeUserId;
	}

	public void setEpisodeUserId(Long episodeUserId) {
		this.episodeUserId = episodeUserId;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="episodeUserUser")
	public User getEpisodeUserUser() {
		return episodeUserUser;
	}

	public void setEpisodeUserUser(User episodeUserUser) {
		this.episodeUserUser = episodeUserUser;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="episodeUserEpisode")
	public Episode getEpisodeUserEpisode() {
		return episodeUserEpisode;
	}

	public void setEpisodeUserEpisode(Episode episodeUserEpisode) {
		this.episodeUserEpisode = episodeUserEpisode;
	}

	@Enumerated(EnumType.STRING)
	public Status getEpisodeUserStatus() {
		return episodeUserStatus;
	}

	public void setEpisodeUserStatus(Status episodeUserStatus) {
		this.episodeUserStatus = episodeUserStatus;
	}

	public Date getEpisodeUserDate() {
		return episodeUserDate;
	}

	public void setEpisodeUserDate(Date episodeUserDate) {
		this.episodeUserDate = episodeUserDate;
	}
	
}
