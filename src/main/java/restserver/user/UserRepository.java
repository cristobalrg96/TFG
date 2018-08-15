package restserver.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long>{

	@Query("FROM User u WHERE u.userLogin=:loginName OR u.userEmail=:loginName")
	public User findUserByLoginName(@Param("loginName") String loginName);
	
	@Query("FROM User u WHERE u.userEmail=:userEmail")
	public User findUserByEmail(@Param("userEmail") String userEmail);

	@Query("FROM User u WHERE u.userLogin=:userLogin")
	public User findUserByLogin(@Param("userLogin") String userLogin);

	public Iterable<User> findByUserLoginContaining(String userLogin);
	
	@Query("SELECT u.userLogin FROM User u where u.userLogin LIKE %:userLogin%")
	public List<String> findUserLoginByUserLoginContaining(@Param("userLogin") String userLogin);
	
}
