package restserver.response;

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

@Entity
public class Response {

	private Long responseId;
	private Comment responseReference;
	private Comment responseComment;
		
	public Response(Comment responseReference, Comment responseComment) {
		this.responseReference = responseReference;
		this.responseComment = responseComment;
	}
	
	public Response() {
	}
	
	@SequenceGenerator( // It only takes effect for
			name = "ResponseIdGenerator", // databases providing identifier
			sequenceName = "ResponseIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ResponseIdGenerator")
	public Long getResponseId() {
		return responseId;
	}
	
	public void setResponseId(Long responseId) {
		this.responseId = responseId;
	}
	
	@OneToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name="responseReference")
	public Comment ResponseReference() {
		return responseReference;
	}
	
	public void setResponseText(Comment responseReference) {
		this.responseReference = responseReference;
	}
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="responseComment")
	public Comment getResponseComment() {
		return responseComment;
	}
	
	public void setResponseComment(Comment responseComment) {
		this.responseComment = responseComment;
	}
	
}
