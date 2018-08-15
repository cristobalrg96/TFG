package restserver.recommendation_people;

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
public class RecommendationPeople {
	
	public Long recommendationPeopleId;
	public People recommendationPeoplePeople;
	public User recommendationPeopleRecommender;
	public User recommendationPeopleRecommended;
	public Date recommendationPeopleDate;
	
	public RecommendationPeople(People recommendationPeoplePeople, User recommendationPeopleRecommender,
			User recommendationPeopleRecommended, Date recommendationPeopleDate) {
		this.recommendationPeoplePeople = recommendationPeoplePeople;
		this.recommendationPeopleRecommender = recommendationPeopleRecommender;
		this.recommendationPeopleRecommended = recommendationPeopleRecommended;
		this.recommendationPeopleDate = recommendationPeopleDate;
	}
	
	public RecommendationPeople() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "RecommendationPeopleIdGenerator", // databases providing identifier
			sequenceName = "RecommendationPeopleIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RecommendationPeopleIdGenerator")
	public Long getRecommendationPeopleId() {
		return recommendationPeopleId;
	}

	public void setRecommendationPeopleId(Long recommendationPeopleId) {
		this.recommendationPeopleId = recommendationPeopleId;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="recommendationPeoplePeople")
	public People getRecommendationPeoplePeople() {
		return recommendationPeoplePeople;
	}

	public void setRecommendationPeoplePeople(People recommendationPeoplePeople) {
		this.recommendationPeoplePeople = recommendationPeoplePeople;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="recommendationPeopleRecommender")
	public User getRecommendationPeopleRecommender() {
		return recommendationPeopleRecommender;
	}

	public void setRecommendationPeopleRecommender(User recommendationPeopleRecommender) {
		this.recommendationPeopleRecommender = recommendationPeopleRecommender;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="recommendationPeopleRecommended")
	public User getRecommendationPeopleRecommended() {
		return recommendationPeopleRecommended;
	}

	public void setRecommendationPeopleRecommended(User recommendationPeopleRecommended) {
		this.recommendationPeopleRecommended = recommendationPeopleRecommended;
	}

	public Date getRecommendationPeopleDate() {
		return recommendationPeopleDate;
	}

	public void setRecommendationPeopleDate(Date recommendationPeopleDate) {
		this.recommendationPeopleDate = recommendationPeopleDate;
	}
	
}
