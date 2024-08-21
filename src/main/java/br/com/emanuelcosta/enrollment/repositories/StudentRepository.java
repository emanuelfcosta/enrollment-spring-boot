package br.com.emanuelcosta.enrollment.repositories;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.emanuelcosta.enrollment.entities.Course;
import br.com.emanuelcosta.enrollment.entities.Student;
import jakarta.transaction.Transactional;


public interface StudentRepository extends JpaRepository<Student, Long> {
	
	
	@Query("SELECT c FROM Course c INNER JOIN c.students s WHERE s.id = :id")
    Set<Course> findCoursesByStudentId( Long id);
	
	
//	I created this method because the delete operation is not allowed 
//	for the many to many bidirectional relationship. At least I didn't 
//	find a better way to do it.
	
	@Transactional
	@Modifying
	@Query(value="delete from tb_student_course tb where tb.student_id=?1 and course_id=?2",
	nativeQuery = true)
	void deleteStudentCourse(@Param("studentId") Long studentId,
			@Param("courseId") Long courseId);

}
