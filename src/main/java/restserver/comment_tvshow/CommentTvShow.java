package restserver.comment_tvshow;

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
import restserver.tvshow.TvShow;

@Entity
public class CommentTvShow {

	private Long commentTvShowId;
	private Comment commentTvShowReference;
	private TvShow commentTvShowTvShow;
		
	public CommentTvShow(Comment commentTvShowReference, TvShow commentTvShowTvShow) {
		this.commentTvShowReference = commentTvShowReference;
		this.commentTvShowTvShow = commentTvShowTvShow;
	}
	
	public CommentTvShow() {
	}
	
	@SequenceGenerator( // It only takes effect for
			name = "CommentTvShowIdGenerator", // databases providing identifier
			sequenceName = "CommentTvShowIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CommentTvShowIdGenerator")
	public Long getCommentTvShowId() {
		return commentTvShowId;
	}
	
	public void setCommentTvShowId(Long commentTvShowId) {
		this.commentTvShowId = commentTvShowId;
	}
	
	@OneToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name="commentTvShowReference")
	public Comment CommentTvShowReference() {
		return commentTvShowReference;
	}
	
	public void setCommentTvShowText(Comment commentTvShowReference) {
		this.commentTvShowReference = commentTvShowReference;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="commentTvShowTvShow")
	public TvShow getCommentTvShowTvShow() {
		return commentTvShowTvShow;
	}
	
	public void setCommentTvShowTvShow(TvShow commentTvShowTvShow) {
		this.commentTvShowTvShow = commentTvShowTvShow;
	}
	
}
