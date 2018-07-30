package com.codingdojo.lessons.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.lessons.models.Teacher;

public interface TeacherRepo extends CrudRepository<Teacher, Long> {
	List<Teacher> findAll();
	
	
	Teacher findByEmail(String search);


	Optional<Teacher> findByTeacherID(long id);
}
