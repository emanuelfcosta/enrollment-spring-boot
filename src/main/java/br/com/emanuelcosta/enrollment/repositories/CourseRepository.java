package br.com.emanuelcosta.enrollment.repositories;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.emanuelcosta.enrollment.entities.Course;
import br.com.emanuelcosta.enrollment.entities.Student;



public interface CourseRepository extends JpaRepository<Course, Long>  {
	
	
	 @Query("SELECT s FROM Student s INNER JOIN s.courses c WHERE c.id = :id")
	    Set<Student> findStudentsByCourseId( Long id);
	

}
