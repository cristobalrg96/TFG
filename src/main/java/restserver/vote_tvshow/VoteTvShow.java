package restserver.vote_tvshow;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restserver.tvshow.TvShow;
import restserver.user.User;

@Entity
public class VoteTvShow {
	
	public Long voteTvShowId;
	public Boolean voteTvShowVote;
	public TvShow voteTvShowTvShow;
	public User voteTvShowUser;
	public Date voteTvShowDate;
	
	public VoteTvShow(Boolean voteTvShowVote, TvShow voteTvShowTvShow, User voteTvShowUser) {
		this.voteTvShowVote = voteTvShowVote;
		this.voteTvShowTvShow = voteTvShowTvShow;
		this.voteTvShowUser = voteTvShowUser;
		this.voteTvShowDate = new Date();
	}
	
	public VoteTvShow() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "VoteTvShowIdGenerator", // databases providing identifier
			sequenceName = "VoteTvShowIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "VoteTvShowIdGenerator")
	public Long getVoteTvShowId() {
		return voteTvShowId;
	}

	public void setVoteTvShowId(Long voteTvShowId) {
		this.voteTvShowId = voteTvShowId;
	}

	public Boolean getVoteTvShowVote() {
		return voteTvShowVote;
	}

	public void setVoteTvShowVote(Boolean voteTvShowVote) {
		this.voteTvShowVote = voteTvShowVote;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="voteTvShowTvShow")
	public TvShow getVoteTvShowTvShow() {
		return voteTvShowTvShow;
	}

	public void setVoteTvShowTvShow(TvShow voteTvShowTvShow) {
		this.voteTvShowTvShow = voteTvShowTvShow;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="voteTvShowUser")
	public User getVoteTvShowUser() {
		return voteTvShowUser;
	}

	public void setVoteTvShowUser(User voteTvShowUser) {
		this.voteTvShowUser = voteTvShowUser;
	}

	public Date getVoteTvShowDate() {
		return voteTvShowDate;
	}

	public void setVoteTvShowDate(Date voteTvShowDate) {
		this.voteTvShowDate = voteTvShowDate;
	}

}
