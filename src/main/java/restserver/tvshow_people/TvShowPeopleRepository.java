package restserver.tvshow_people;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import restserver.people.People;
import restserver.tvshow.TvShow;


public interface TvShowPeopleRepository extends CrudRepository<TvShowPeople,Long> {

	@Query("FROM TvShowPeople t WHERE t.tvShowPeopleTvShow=:tvShowPeopleTvShow AND t.tvShowPeoplePeople=:tvShowPeoplePeople"
							+ " AND t.tvShowPeopleJob like :tvShowPeopleJob")
	public TvShowPeople findTvShowPeopleByTvShowAndPeopleAndJob(@Param("tvShowPeopleTvShow")TvShow tvShowPeopleTvShow, 
			@Param("tvShowPeoplePeople") People tvShowPeoplePeople, @Param("tvShowPeopleJob") String tvShowPeopleJob);
	
	
}
