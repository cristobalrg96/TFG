package restserver.comment;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;

import restserver.user.User;

@Entity
@BatchSize(size=50)
public class Comment {
	public Long commentId;
	public String commentText;
	public User commentUser;
	public Date commentDate;
	
	public Comment(String commentText, User commentUser) {
		this.commentText = commentText;
		this.commentUser = commentUser;
		this.commentDate = new Date();
	}
	
	public Comment() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "CommentIdGenerator", // databases providing identifier
			sequenceName = "CommentIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CommentIdGenerator")
	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="commentUser")
	public User getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(User commentUser) {
		this.commentUser = commentUser;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	
	
	
}
