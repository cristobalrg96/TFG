package restserver.people_user;

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

import restserver.people.People;
import restserver.user.User;
import restserver.util.Status;

@Entity
public class PeopleUser {

	Long peopleUserId;
	User peopleUserUser;
	People peopleUserPeople;
	Status peopleUserStatus;
	Date peopleUserDate;
	
	public PeopleUser(User peopleUserUser, People peopleUserPeople, Status peopleUserStatus) {
		this.peopleUserUser = peopleUserUser;
		this.peopleUserPeople = peopleUserPeople;
		this.peopleUserStatus = peopleUserStatus;
		this.peopleUserDate = new Date();
	}

	public PeopleUser() {
	}

	@SequenceGenerator( // It only takes effect for
			name = "PeopleUserIdGenerator", // databases providing identifier
			sequenceName = "PeopleUserIdSeq")
			// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PeopleUserIdGenerator")
	public Long getPeopleUserId() {
		return peopleUserId;
	}

	public void setPeopleUserId(Long peopleUserId) {
		this.peopleUserId = peopleUserId;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="peopleUserUser")
	public User getPeopleUserUser() {
		return peopleUserUser;
	}

	public void setPeopleUserUser(User peopleUserUser) {
		this.peopleUserUser = peopleUserUser;
	}

	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="peopleUserPeople")
	public People getPeopleUserPeople() {
		return peopleUserPeople;
	}

	public void setPeopleUserPeople(People peopleUserPeople) {
		this.peopleUserPeople = peopleUserPeople;
	}

	@Enumerated(EnumType.STRING)
	public Status getPeopleUserStatus() {
		return peopleUserStatus;
	}

	public void setPeopleUserStatus(Status peopleUserStatus) {
		this.peopleUserStatus = peopleUserStatus;
	}

	public Date getPeopleUserDate() {
		return peopleUserDate;
	}

	public void setPeopleUserDate(Date peopleUserDate) {
		this.peopleUserDate = peopleUserDate;
	}
	
}
