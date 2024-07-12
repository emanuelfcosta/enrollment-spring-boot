package br.com.emanuelcosta.enrollment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emanuelcosta.enrollment.entities.Course;



public interface CourseRepository extends JpaRepository<Course, Long>  {

}
