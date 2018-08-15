package restserver.vote_comment;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restserver.comment.Comment;
import restserver.user.User;

@Entity
public class VoteComment {
	
	public Long voteCommentId;
	public Boolean voteCommentVote;
	public Comment voteCommentComment;
	public User voteCommentUser;
	public Date voteCommentDate;
	
	public VoteComment(Boolean voteCommentVote, Comment voteCommentComment, User voteCommentUser) {
		this.voteCommentVote = voteCommentVote;
		this.voteCommentComment = voteCommentComment;
		this.voteCommentUser = voteCommentUser;
		this.voteCommentDate = new Date();
	}
	
	public VoteComment() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "VoteCommentIdGenerator", // databases providing identifier
			sequenceName = "VoteCommentIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "VoteCommentIdGenerator")
	public Long getVoteCommentId() {
		return voteCommentId;
	}

	public void setVoteCommentId(Long voteCommentId) {
		this.voteCommentId = voteCommentId;
	}

	public Boolean getVoteCommentVote() {
		return voteCommentVote;
	}

	public void setVoteCommentVote(Boolean voteCommentVote) {
		this.voteCommentVote = voteCommentVote;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="voteCommentComment")
	public Comment getVoteCommentComment() {
		return voteCommentComment;
	}

	public void setVoteCommentComment(Comment voteCommentComment) {
		this.voteCommentComment = voteCommentComment;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="voteCommentUser")
	public User getVoteCommentUser() {
		return voteCommentUser;
	}

	public void setVoteCommentUser(User voteCommentUser) {
		this.voteCommentUser = voteCommentUser;
	}

	public Date getVoteCommentDate() {
		return voteCommentDate;
	}

	public void setVoteCommentDate(Date voteCommentDate) {
		this.voteCommentDate = voteCommentDate;
	}

}
