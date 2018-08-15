package restserver.tvshow_user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restserver.tvshow.TvShow;
import restserver.user.User;
import restserver.util.Status;

@Entity
public class TvShowUser {

	Long tvShowUserId;
	User tvShowUserUser;
	TvShow tvShowUserTvShow;
	Status tvShowUserStatus;
	Date tvShowUserDate;
	
	public TvShowUser(User tvShowUserUser, TvShow tvShowUserTvShow, Status tvShowUserStatus) {
		this.tvShowUserUser = tvShowUserUser;
		this.tvShowUserTvShow = tvShowUserTvShow;
		this.tvShowUserStatus = tvShowUserStatus;
		this.tvShowUserDate = new Date();
	}

	public TvShowUser() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "TvShowUserIdGenerator", // databases providing identifier
			sequenceName = "TvShowUserIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TvShowUserIdGenerator")
	public Long getTvShowUserId() {
		return tvShowUserId;
	}

	public void setTvShowUserId(Long tvShowUserId) {
		this.tvShowUserId = tvShowUserId;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="tvShowUserUser")
	public User getTvShowUserUser() {
		return tvShowUserUser;
	}

	public void setTvShowUserUser(User tvShowUserUser) {
		this.tvShowUserUser = tvShowUserUser;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="tvShowUserTvShow")
	public TvShow getTvShowUserTvShow() {
		return tvShowUserTvShow;
	}

	public void setTvShowUserTvShow(TvShow tvShowUserTvShow) {
		this.tvShowUserTvShow = tvShowUserTvShow;
	}

	@Enumerated(EnumType.STRING)
	public Status getTvShowUserStatus() {
		return tvShowUserStatus;
	}

	public void setTvShowUserStatus(Status tvShowUserStatus) {
		this.tvShowUserStatus = tvShowUserStatus;
	}

	public Date getTvShowUserDate() {
		return tvShowUserDate;
	}

	public void setTvShowUserDate(Date tvShowUserDate) {
		this.tvShowUserDate = tvShowUserDate;
	}
	
}
