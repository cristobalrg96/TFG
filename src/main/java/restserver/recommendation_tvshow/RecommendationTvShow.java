package restserver.recommendation_tvshow;

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
public class RecommendationTvShow {
	
	public Long recommendationTvShowId;
	public TvShow recommendationTvShowTvShow;
	public User recommendationTvShowRecommender;
	public User recommendationTvShowRecommended;
	public Date recommendationTvShowDate;
	
	public RecommendationTvShow(TvShow recommendationTvShowTvShow, User recommendationTvShowRecommender,
			User recommendationTvShowRecommended, Date recommendationTvShowDate) {
		this.recommendationTvShowTvShow = recommendationTvShowTvShow;
		this.recommendationTvShowRecommender = recommendationTvShowRecommender;
		this.recommendationTvShowRecommended = recommendationTvShowRecommended;
		this.recommendationTvShowDate = recommendationTvShowDate;
	}
	
	public RecommendationTvShow() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "RecommendationTvShowIdGenerator", // databases providing identifier
			sequenceName = "RecommendationTvShowIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RecommendationTvShowIdGenerator")
	public Long getRecommendationTvShowId() {
		return recommendationTvShowId;
	}

	public void setRecommendationTvShowId(Long recommendationTvShowId) {
		this.recommendationTvShowId = recommendationTvShowId;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="recommendationTvShowTvShow")
	public TvShow getRecommendationTvShowTvShow() {
		return recommendationTvShowTvShow;
	}

	public void setRecommendationTvShowTvShow(TvShow recommendationTvShowTvShow) {
		this.recommendationTvShowTvShow = recommendationTvShowTvShow;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="recommendationTvShowRecommender")
	public User getRecommendationTvShowRecommender() {
		return recommendationTvShowRecommender;
	}

	public void setRecommendationTvShowRecommender(User recommendationTvShowRecommender) {
		this.recommendationTvShowRecommender = recommendationTvShowRecommender;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="recommendationTvShowRecommended")
	public User getRecommendationTvShowRecommended() {
		return recommendationTvShowRecommended;
	}

	public void setRecommendationTvShowRecommended(User recommendationTvShowRecommended) {
		this.recommendationTvShowRecommended = recommendationTvShowRecommended;
	}

	public Date getRecommendationTvShowDate() {
		return recommendationTvShowDate;
	}

	public void setRecommendationTvShowDate(Date recommendationTvShowDate) {
		this.recommendationTvShowDate = recommendationTvShowDate;
	}
	
}
