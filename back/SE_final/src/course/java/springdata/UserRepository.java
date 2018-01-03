package course.java.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import course.java.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	@Query(value = "Select * from USER where ACCOUNT = ?1", nativeQuery=true)
	public User findByAccount(String account);
	
	@Query(value = "Select * from USER where ROLE = ?1", nativeQuery=true)
	public List<User> findByRole(String role);
}
