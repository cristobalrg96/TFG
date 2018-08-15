package restserver.util;

public class UserContentStatusDto {
	
	public Long userId;
	public Long contentId;
	public Status status;
	
	public UserContentStatusDto(Long userId, Long contentId, Status status) {
		this.userId = userId;
		this.contentId = contentId;
		this.status = status;
	}

	public UserContentStatusDto() {
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
