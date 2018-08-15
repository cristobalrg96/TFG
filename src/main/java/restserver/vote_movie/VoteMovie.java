package restserver.vote_movie;

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
public class VoteMovie {
	
	public Long voteMovieId;
	public Boolean voteMovieVote;
	public Movie voteMovieMovie;
	public User voteMovieUser;
	public Date voteMovieDate;
	
	public VoteMovie(Boolean voteMovieVote, Movie voteMovieMovie, User voteMovieUser) {
		this.voteMovieVote = voteMovieVote;
		this.voteMovieMovie = voteMovieMovie;
		this.voteMovieUser = voteMovieUser;
		this.voteMovieDate = new Date();
	}
	
	public VoteMovie() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "VoteMovieIdGenerator", // databases providing identifier
			sequenceName = "VoteMovieIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "VoteMovieIdGenerator")
	public Long getVoteMovieId() {
		return voteMovieId;
	}

	public void setVoteMovieId(Long voteMovieId) {
		this.voteMovieId = voteMovieId;
	}

	public Boolean getVoteMovieVote() {
		return voteMovieVote;
	}

	public void setVoteMovieVote(Boolean voteMovieVote) {
		this.voteMovieVote = voteMovieVote;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="voteMovieMovie")
	public Movie getVoteMovieMovie() {
		return voteMovieMovie;
	}

	public void setVoteMovieMovie(Movie voteMovieMovie) {
		this.voteMovieMovie = voteMovieMovie;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="voteMovieUser")
	public User getVoteMovieUser() {
		return voteMovieUser;
	}

	public void setVoteMovieUser(User voteMovieUser) {
		this.voteMovieUser = voteMovieUser;
	}

	public Date getVoteMovieDate() {
		return voteMovieDate;
	}

	public void setVoteMovieDate(Date voteMovieDate) {
		this.voteMovieDate = voteMovieDate;
	}

}

