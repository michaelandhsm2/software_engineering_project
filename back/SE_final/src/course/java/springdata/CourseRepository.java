package course.java.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import course.java.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
	@Query(value = "Select * from COURSE where PROFESSORACCOUNT = ?1", nativeQuery=true)
	public List<Course> findByProfessor(String professor);
	
	@Query(value = "Select * from COURSE where COURSEID = ?1", nativeQuery=true)
	public Course findByCourseId(int courseId);
}
