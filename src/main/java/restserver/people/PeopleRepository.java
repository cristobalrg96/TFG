package restserver.people;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PeopleRepository extends CrudRepository<People,Long> {

	public Iterable<People> findByPeopleNameContaining(String peopleName);
	
	@Query("FROM People p WHERE p.peopleApiId=:peopleApiId")
	public People findPeopleByPeopleApiId(@Param("peopleApiId") Long peopleApiId);
	
	@Query("SELECT p.peopleName FROM People p where p.peopleName LIKE %:peopleName%")
	public List<String> findPeopleNameByPeopleNameContaining(@Param("peopleName") String peopleName);
}
