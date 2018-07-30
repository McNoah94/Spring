package com.codingdojo.lessons.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.lessons.models.Student;

public interface StudentRepo extends CrudRepository<Student, Long>{

}
