package course.java.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import course.java.entity.UserHw;

public interface UserHwRepository extends CrudRepository<UserHw, Integer> {
	@Query(value = "Select * from USERHW where USERACCOUNT = ?1 && HWID = ?2", nativeQuery=true)
	public UserHw findByUserAccountAndHwId(String userAccount, int hwId);
	
	@Query(value = "Select * from USERHW where USERACCOUNT = ?1", nativeQuery=true)
	public List<UserHw> findByUserAccount(String userAccount);
}
