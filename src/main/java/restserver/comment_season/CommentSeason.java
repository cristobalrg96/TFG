package restserver.comment_season;

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
import restserver.season.Season;

@Entity
public class CommentSeason {

	private Long commentSeasonId;
	private Comment commentSeasonReference;
	private Season commentSeasonSeason;
		
	public CommentSeason(Comment commentSeasonReference, Season commentSeasonSeason) {
		this.commentSeasonReference = commentSeasonReference;
		this.commentSeasonSeason = commentSeasonSeason;
	}
	
	public CommentSeason() {
	}
	
	@SequenceGenerator( // It only takes effect for
			name = "CommentSeasonIdGenerator", // databases providing identifier
			sequenceName = "CommentSeasonIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CommentSeasonIdGenerator")
	public Long getCommentSeasonId() {
		return commentSeasonId;
	}
	
	public void setCommentSeasonId(Long commentSeasonId) {
		this.commentSeasonId = commentSeasonId;
	}
	
	@OneToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name="commentSeasonReference")
	public Comment CommentSeasonReference() {
		return commentSeasonReference;
	}
	
	public void setCommentSeasonText(Comment commentSeasonReference) {
		this.commentSeasonReference = commentSeasonReference;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="commentSeasonSeason")
	public Season getCommentSeasonSeason() {
		return commentSeasonSeason;
	}
	
	public void setCommentSeasonSeason(Season commentSeasonSeason) {
		this.commentSeasonSeason = commentSeasonSeason;
	}
	
}

