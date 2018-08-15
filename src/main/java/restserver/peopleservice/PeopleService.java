package restserver.peopleservice;

import restserver.people.People;

public interface PeopleService {

	People findPeopleById(Long id);

	Iterable<People> findPeopleByPeopleName(String peopleLogin);

}
