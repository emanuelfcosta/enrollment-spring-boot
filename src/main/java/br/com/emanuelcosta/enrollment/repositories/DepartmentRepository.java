package br.com.emanuelcosta.enrollment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emanuelcosta.enrollment.entities.Department;



public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
