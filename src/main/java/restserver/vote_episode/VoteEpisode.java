package restserver.vote_episode;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restserver.episode.Episode;
import restserver.user.User;

@Entity
public class VoteEpisode {
	
	public Long voteEpisodeId;
	public Boolean voteEpisodeVote;
	public Episode voteEpisodeEpisode;
	public User voteEpisodeUser;
	public Date voteEpisodeDate;
	
	public VoteEpisode(Boolean voteEpisodeVote, Episode voteEpisodeEpisode, User voteEpisodeUser) {
		this.voteEpisodeVote = voteEpisodeVote;
		this.voteEpisodeEpisode = voteEpisodeEpisode;
		this.voteEpisodeUser = voteEpisodeUser;
		this.voteEpisodeDate = new Date();
	}
	
	public VoteEpisode() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "VoteEpisodeIdGenerator", // databases providing identifier
			sequenceName = "VoteEpisodeIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "VoteEpisodeIdGenerator")
	public Long getVoteEpisodeId() {
		return voteEpisodeId;
	}

	public void setVoteEpisodeId(Long voteEpisodeId) {
		this.voteEpisodeId = voteEpisodeId;
	}

	public Boolean getVoteEpisodeVote() {
		return voteEpisodeVote;
	}

	public void setVoteEpisodeVote(Boolean voteEpisodeVote) {
		this.voteEpisodeVote = voteEpisodeVote;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="voteEpisodeEpisode")
	public Episode getVoteEpisodeEpisode() {
		return voteEpisodeEpisode;
	}

	public void setVoteEpisodeEpisode(Episode voteEpisodeEpisode) {
		this.voteEpisodeEpisode = voteEpisodeEpisode;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="voteEpisodeUser")
	public User getVoteEpisodeUser() {
		return voteEpisodeUser;
	}

	public void setVoteEpisodeUser(User voteEpisodeUser) {
		this.voteEpisodeUser = voteEpisodeUser;
	}

	public Date getVoteEpisodeDate() {
		return voteEpisodeDate;
	}

	public void setVoteEpisodeDate(Date voteEpisodeDate) {
		this.voteEpisodeDate = voteEpisodeDate;
	}

}
