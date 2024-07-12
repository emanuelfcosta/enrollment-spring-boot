package br.com.emanuelcosta.enrollment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emanuelcosta.enrollment.entities.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {

}
