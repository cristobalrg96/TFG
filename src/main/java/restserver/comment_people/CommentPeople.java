package restserver.comment_people;

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
import restserver.people.People;

@Entity
public class CommentPeople {

	private Long commentPeopleId;
	private Comment commentPeopleReference;
	private People commentPeoplePeople;
		
	public CommentPeople(Comment commentPeopleReference, People commentPeoplePeople) {
		this.commentPeopleReference = commentPeopleReference;
		this.commentPeoplePeople = commentPeoplePeople;
	}
	
	public CommentPeople() {
	}
	
	@SequenceGenerator( // It only takes effect for
			name = "CommentPeopleIdGenerator", // databases providing identifier
			sequenceName = "CommentPeopleIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CommentPeopleIdGenerator")
	public Long getCommentPeopleId() {
		return commentPeopleId;
	}
	
	public void setCommentPeopleId(Long commentPeopleId) {
		this.commentPeopleId = commentPeopleId;
	}
	
	@OneToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name="commentPeopleReference")
	public Comment CommentPeopleReference() {
		return commentPeopleReference;
	}
	
	public void setCommentPeopleText(Comment commentPeopleReference) {
		this.commentPeopleReference = commentPeopleReference;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="commentPeoplePeople")
	public People getCommentPeoplePeople() {
		return commentPeoplePeople;
	}
	
	public void setCommentPeoplePeople(People commentPeoplePeople) {
		this.commentPeoplePeople = commentPeoplePeople;
	}
	
}
