package course.java.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import course.java.entity.UserCourse;

public interface UserCourseRepository extends CrudRepository<UserCourse, Integer> {
	@Query(value = "Select * from USERCOURSE where USERACCOUNT = ?1", nativeQuery=true)
	public List<UserCourse> findByUserAccount(String userAccount);
	
	@Query(value = "Select * from USERCOURSE where COURSEID = ?1", nativeQuery=true)
	public List<UserCourse> findByCourseId(int subjectId);
}
