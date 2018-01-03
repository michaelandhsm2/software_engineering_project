package course.java.springdata;

import org.springframework.data.repository.CrudRepository;

import course.java.entity.FileEntity;


public interface FileRepository extends CrudRepository<FileEntity, Integer> {

}
