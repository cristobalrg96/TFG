package restserver.recommendation_movie;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restserver.movie.Movie;
import restserver.user.User;

@Entity
public class RecommendationMovie {
	
	public Long recommendationMovieId;
	public Movie recommendationMovieMovie;
	public User recommendationMovieRecommender;
	public User recommendationMovieRecommended;
	public Date recommendationMovieDate;
	
	public RecommendationMovie(Movie recommendationMovieMovie, User recommendationMovieRecommender,
			User recommendationMovieRecommended, Date recommendationMovieDate) {
		this.recommendationMovieMovie = recommendationMovieMovie;
		this.recommendationMovieRecommender = recommendationMovieRecommender;
		this.recommendationMovieRecommended = recommendationMovieRecommended;
		this.recommendationMovieDate = recommendationMovieDate;
	}
	
	public RecommendationMovie() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "RecommendationMovieIdGenerator", // databases providing identifier
			sequenceName = "RecommendationMovieIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RecommendationMovieIdGenerator")
	public Long getRecommendationMovieId() {
		return recommendationMovieId;
	}

	public void setRecommendationMovieId(Long recommendationMovieId) {
		this.recommendationMovieId = recommendationMovieId;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="recommendationMovieMovie")
	public Movie getRecommendationMovieMovie() {
		return recommendationMovieMovie;
	}

	public void setRecommendationMovieMovie(Movie recommendationMovieMovie) {
		this.recommendationMovieMovie = recommendationMovieMovie;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="recommendationMovieRecommender")
	public User getRecommendationMovieRecommender() {
		return recommendationMovieRecommender;
	}

	public void setRecommendationMovieRecommender(User recommendationMovieRecommender) {
		this.recommendationMovieRecommender = recommendationMovieRecommender;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="recommendationMovieRecommended")
	public User getRecommendationMovieRecommended() {
		return recommendationMovieRecommended;
	}

	public void setRecommendationMovieRecommended(User recommendationMovieRecommended) {
		this.recommendationMovieRecommended = recommendationMovieRecommended;
	}

	public Date getRecommendationMovieDate() {
		return recommendationMovieDate;
	}

	public void setRecommendationMovieDate(Date recommendationMovieDate) {
		this.recommendationMovieDate = recommendationMovieDate;
	}
	
}
