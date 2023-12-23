package com.crud.repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {

	public List<Student> findAll();
	
	public Student findById(int id);

	public boolean existsById(int id);

	public void deleteById(int id);
}
