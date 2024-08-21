package br.com.emanuelcosta.enrollment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.emanuelcosta.enrollment.entities.Course;
import br.com.emanuelcosta.enrollment.entities.Department;
import br.com.emanuelcosta.enrollment.entities.Instructor;
import br.com.emanuelcosta.enrollment.entities.Student;
import br.com.emanuelcosta.enrollment.repositories.DepartmentRepository;
import br.com.emanuelcosta.enrollment.repositories.InstructorRepository;
import br.com.emanuelcosta.enrollment.services.exceptions.DatabaseException;
import br.com.emanuelcosta.enrollment.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	//private InstructorRepository instructorRepository;
	
	public List<Department> findAll(){
		

		
		return departmentRepository.findAll();
		
	}
	
	public Department findById(Long id) {
		Optional<Department> obj = departmentRepository.findById(id);
		
		
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	public List<Instructor> findAllInstructorsByDepartmentId(Long departmentId){
			
		
		Department obj = departmentRepository.findById(departmentId).get();
		
		return obj.getInstructors();
		
		
	}
	
	
	
	public Department insert(Department obj) {
		
	    return departmentRepository.save(obj);
		
	}
	
	public void delete(Long id) {
		
	
		
		
		
		try {
			
			Department obj = departmentRepository.findById(id).get();
			
		   //before deleting we set the instructors as null
			
			for (Instructor instructor : obj.getInstructors() ) {
				instructor.setDepartment(null);
			  
			}
			
			
			departmentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	public Department update(Long id,  Department obj) {

		
		try {
			Department entity = departmentRepository.getReferenceById(id);
			updateData(entity, obj);
			return departmentRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
		
		
	}

	private void updateData(Department entity, Department obj) {
		// TODO Auto-generated method stub
		
		entity.setName(obj.getName());
		
	}

}
