package restserver.comment_episode;

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
import restserver.episode.Episode;

@Entity
public class CommentEpisode {

	private Long commentEpisodeId;
	private Comment commentEpisodeReference;
	private Episode commentEpisodeEpisode;
		
	public CommentEpisode(Comment commentEpisodeReference, Episode commentEpisodeEpisode) {
		this.commentEpisodeReference = commentEpisodeReference;
		this.commentEpisodeEpisode = commentEpisodeEpisode;
	}
	
	public CommentEpisode() {
	}
	
	@SequenceGenerator( // It only takes effect for
			name = "CommentEpisodeIdGenerator", // databases providing identifier
			sequenceName = "CommentEpisodeIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CommentEpisodeIdGenerator")
	public Long getCommentEpisodeId() {
		return commentEpisodeId;
	}
	
	public void setCommentEpisodeId(Long commentEpisodeId) {
		this.commentEpisodeId = commentEpisodeId;
	}
	
	@OneToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name="commentEpisodeReference")
	public Comment CommentEpisodeReference() {
		return commentEpisodeReference;
	}
	
	public void setCommentEpisodeText(Comment commentEpisodeReference) {
		this.commentEpisodeReference = commentEpisodeReference;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="commentEpisodeEpisode")
	public Episode getCommentEpisodeEpisode() {
		return commentEpisodeEpisode;
	}
	
	public void setCommentEpisodeEpisode(Episode commentEpisodeEpisode) {
		this.commentEpisodeEpisode = commentEpisodeEpisode;
	}
	
}
