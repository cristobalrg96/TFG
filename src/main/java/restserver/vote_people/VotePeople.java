package restserver.vote_people;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restserver.people.People;
import restserver.user.User;

@Entity
public class VotePeople {
	
	public Long votePeopleId;
	public Boolean votePeopleVote;
	public People votePeoplePeople;
	public User votePeopleUser;
	public Date votePeopleDate;
	
	public VotePeople(Boolean votePeopleVote, People votePeoplePeople, User votePeopleUser) {
		this.votePeopleVote = votePeopleVote;
		this.votePeoplePeople = votePeoplePeople;
		this.votePeopleUser = votePeopleUser;
		this.votePeopleDate = new Date();
	}
	
	public VotePeople() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "VotePeopleIdGenerator", // databases providing identifier
			sequenceName = "VotePeopleIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "VotePeopleIdGenerator")
	public Long getVotePeopleId() {
		return votePeopleId;
	}

	public void setVotePeopleId(Long votePeopleId) {
		this.votePeopleId = votePeopleId;
	}

	public Boolean getVotePeopleVote() {
		return votePeopleVote;
	}

	public void setVotePeopleVote(Boolean votePeopleVote) {
		this.votePeopleVote = votePeopleVote;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="votePeoplePeople")
	public People getVotePeoplePeople() {
		return votePeoplePeople;
	}

	public void setVotePeoplePeople(People votePeoplePeople) {
		this.votePeoplePeople = votePeoplePeople;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="votePeopleUser")
	public User getVotePeopleUser() {
		return votePeopleUser;
	}

	public void setVotePeopleUser(User votePeopleUser) {
		this.votePeopleUser = votePeopleUser;
	}

	public Date getVotePeopleDate() {
		return votePeopleDate;
	}

	public void setVotePeopleDate(Date votePeopleDate) {
		this.votePeopleDate = votePeopleDate;
	}

}
