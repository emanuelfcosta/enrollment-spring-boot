package br.com.emanuelcosta.enrollment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emanuelcosta.enrollment.entities.Instructor;


public interface InstructorRepository extends JpaRepository<Instructor, Long> {
	
}

