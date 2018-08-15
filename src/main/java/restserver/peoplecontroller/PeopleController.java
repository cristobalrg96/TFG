package restserver.peoplecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restserver.people.People;
import restserver.peopleservice.PeopleService;

@RestController
public class PeopleController {
	
	@Autowired
	PeopleService peopleService;
	
	@RequestMapping("/people/{id}")
	public People findPeopleById(@PathVariable(value="id") Long id) {
		return peopleService.findPeopleById(id);
	}
	
	@RequestMapping("/people")
	public Iterable<People> findPeopleByName(@RequestParam("name") String peopleName) {
		return peopleService.findPeopleByPeopleName(peopleName);
	}
	
}
