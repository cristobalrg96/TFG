package restserver.peopleservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import restserver.people.People;
import restserver.people.PeopleRepository;

@Service("peopleService")
@Transactional
public class PeopleServiceImpl implements PeopleService{

	@Autowired
	PeopleRepository peopleRepository;
	
	@Override
	@Transactional(readOnly = true)
	public People findPeopleById(Long id) {
		return peopleRepository.findOne(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<People> findPeopleByPeopleName(String peopleLogin) {
		return peopleRepository.findByPeopleNameContaining(peopleLogin);
	}
	
	
}
