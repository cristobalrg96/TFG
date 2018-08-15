package restserver.people_user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import restserver.people.People;
import restserver.user.User;

public interface PeopleUserRepository extends CrudRepository<PeopleUser, Long>{

	@Query("FROM PeopleUser p WHERE p.peopleUserPeople =:peopleUserPeople AND p.peopleUserUser =:peopleUserUser")
	public PeopleUser findPeopleUserByPeopleAndUser(@Param("peopleUserPeople") People peopleUserPeople, @Param("peopleUserUser") User peopleUserUser);
	
	
}