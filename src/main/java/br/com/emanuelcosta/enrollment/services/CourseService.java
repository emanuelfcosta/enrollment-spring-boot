package br.com.emanuelcosta.enrollment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.emanuelcosta.enrollment.entities.Course;
import br.com.emanuelcosta.enrollment.entities.Instructor;
import br.com.emanuelcosta.enrollment.repositories.CourseRepository;
import br.com.emanuelcosta.enrollment.repositories.InstructorRepository;
import br.com.emanuelcosta.enrollment.repositories.StudentRepository;
import br.com.emanuelcosta.enrollment.services.exceptions.DatabaseException;
import br.com.emanuelcosta.enrollment.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Course> findAll(){
		
		return courseRepository.findAll();
		
	}
	
	public Course findById(Long id) {
		Optional<Course> obj = courseRepository.findById(id);
		
		return obj.get();
	}
	
	public Course createCourse(Long instructorId, Course course) {
    	
    	
    	try {
    		
    		Instructor instructorEntity = instructorRepository.findById(instructorId).get();
    		
    		
			return courseRepository.save(course);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(instructorId);
		}	
    		
	}
	
	
	public Course insert(Course obj) {
		
	    return courseRepository.save(obj);
		
	}
	
	
	public void delete(Long id) {
		
	
			
			try {
	
				courseRepository.deleteById(id);
			} catch (EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException(id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException(e.getMessage());
			}
			
		}
	
	public Course update(Long id,  Course obj) {
		
		try {
			Course entity = courseRepository.getReferenceById(id);
			
			entity.setName(obj.getName());
			 
			
			return courseRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
		
		
	}

}

