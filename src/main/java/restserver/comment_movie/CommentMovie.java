package restserver.comment_movie;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import restserver.comment.Comment;
import restserver.movie.Movie;

@Entity
public class CommentMovie {

	private Long commentMovieId;
	private Comment commentMovieReference;
	private Movie commentMovieMovie;
		
	public CommentMovie(Comment commentMovieReference, Movie commentMovieMovie) {
		this.commentMovieReference = commentMovieReference;
		this.commentMovieMovie = commentMovieMovie;
	}
	
	public CommentMovie() {
	}
	
	@SequenceGenerator( // It only takes effect for
			name = "CommentMovieIdGenerator", // databases providing identifier
			sequenceName = "CommentMovieIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CommentMovieIdGenerator")
	public Long getCommentMovieId() {
		return commentMovieId;
	}
	
	public void setCommentMovieId(Long commentMovieId) {
		this.commentMovieId = commentMovieId;
	}
	
	@OneToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name="commentMovieReference")
	public Comment CommentMovieReference() {
		return commentMovieReference;
	}
	
	public void setCommentMovieText(Comment commentMovieReference) {
		this.commentMovieReference = commentMovieReference;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="commentMovieMovie")
	public Movie getCommentMovieMovie() {
		return commentMovieMovie;
	}
	
	public void setCommentMovieMovie(Movie commentMovieMovie) {
		this.commentMovieMovie = commentMovieMovie;
	}
	
}

