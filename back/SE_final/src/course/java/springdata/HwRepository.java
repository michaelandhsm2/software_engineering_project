package course.java.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import course.java.entity.Hw;

public interface HwRepository extends CrudRepository<Hw, Integer> {
	@Query(value = "Select * from HW where COURSEID = ?1", nativeQuery=true)
	public List<Hw> findByCourseId(int subjectId);
	
	@Query(value = "Select * from HW where COURSEID = ?1", nativeQuery=true)
	public List<Hw> findAllByclassId(int courseId);
	
}
