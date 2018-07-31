package com.codingdojo.lessons.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.codingdojo.lessons.models.Teacher;
@CrossOrigin(origins = "http://localhost:4200")
public interface TeacherRepo extends CrudRepository<Teacher, Long> {
	List<Teacher> findAll();
	
	
	Teacher findByEmail(String search);


	Optional<Teacher> findByTeacherID(long id);
}
