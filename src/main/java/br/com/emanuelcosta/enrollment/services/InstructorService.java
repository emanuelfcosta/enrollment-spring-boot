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
import br.com.emanuelcosta.enrollment.repositories.CourseRepository;
import br.com.emanuelcosta.enrollment.repositories.DepartmentRepository;
import br.com.emanuelcosta.enrollment.repositories.InstructorRepository;
import br.com.emanuelcosta.enrollment.services.exceptions.DatabaseException;
import br.com.emanuelcosta.enrollment.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class InstructorService {
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	

	
	public List<Instructor> findAll(){
		
		return instructorRepository.findAll();
		
	}
	
	public Instructor findById(Long id) {
		Optional<Instructor> obj = instructorRepository.findById(id);
		
		return obj.get();
	}
	
	public List<Instructor> findByDepartmentId(Long departmentId) {
		
			
		try {
    		
    		Department departmentEntity = departmentRepository.findById(departmentId).get();
    		
    		List<Instructor> obj = departmentEntity.getInstructors(); 
    					
			return obj;
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(departmentId);
		}	
		
		
		
	}
	
	
    public Instructor createInstructor(Long departmentId, Long courseId, Instructor instructor) {
    	
    	
    	try {
    		
    		Department departmentEntity = departmentRepository.findById(departmentId).get();
    		
    		Course courseEntity = courseRepository.findById(courseId).get();
    		
    		instructor.setDepartment(departmentEntity);
    		
    		instructor.setCourse(courseEntity);
			
			return instructorRepository.save(instructor);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(departmentId);
		}	
    		
	}
	
	
	public Instructor insert(Instructor obj) {
		
	    return instructorRepository.save(obj);
		
	}
	
	public void delete(Long id) {
			
			try {
				instructorRepository.deleteById(id);
			} catch (EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException(id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException(e.getMessage());
			}
			
		}
	
	public Instructor update(Long id,  Instructor obj) {
		
		try {
			Instructor entity = instructorRepository.getReferenceById(id);
			
			entity.setName(obj.getName());
			entity.setEmail(obj.getEmail()); 
			
			
			return instructorRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
		
		
	}

}

