package restserver.vote_season;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restserver.season.Season;
import restserver.user.User;

@Entity
public class VoteSeason {
	
	public Long voteSeasonId;
	public Boolean voteSeasonVote;
	public Season voteSeasonSeason;
	public User voteSeasonUser;
	public Date voteSeasonDate;
	
	public VoteSeason(Boolean voteSeasonVote, Season voteSeasonSeason, User voteSeasonUser) {
		this.voteSeasonVote = voteSeasonVote;
		this.voteSeasonSeason = voteSeasonSeason;
		this.voteSeasonUser = voteSeasonUser;
		this.voteSeasonDate = new Date();
	}
	
	public VoteSeason() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "VoteSeasonIdGenerator", // databases providing identifier
			sequenceName = "VoteSeasonIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "VoteSeasonIdGenerator")
	public Long getVoteSeasonId() {
		return voteSeasonId;
	}

	public void setVoteSeasonId(Long voteSeasonId) {
		this.voteSeasonId = voteSeasonId;
	}

	public Boolean getVoteSeasonVote() {
		return voteSeasonVote;
	}

	public void setVoteSeasonVote(Boolean voteSeasonVote) {
		this.voteSeasonVote = voteSeasonVote;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="voteSeasonSeason")
	public Season getVoteSeasonSeason() {
		return voteSeasonSeason;
	}

	public void setVoteSeasonSeason(Season voteSeasonSeason) {
		this.voteSeasonSeason = voteSeasonSeason;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="voteSeasonUser")
	public User getVoteSeasonUser() {
		return voteSeasonUser;
	}

	public void setVoteSeasonUser(User voteSeasonUser) {
		this.voteSeasonUser = voteSeasonUser;
	}

	public Date getVoteSeasonDate() {
		return voteSeasonDate;
	}

	public void setVoteSeasonDate(Date voteSeasonDate) {
		this.voteSeasonDate = voteSeasonDate;
	}

}
