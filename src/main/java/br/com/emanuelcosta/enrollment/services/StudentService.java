package br.com.emanuelcosta.enrollment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.emanuelcosta.enrollment.entities.Course;
import br.com.emanuelcosta.enrollment.entities.Student;
import br.com.emanuelcosta.enrollment.repositories.CourseRepository;
import br.com.emanuelcosta.enrollment.repositories.StudentRepository;
import br.com.emanuelcosta.enrollment.services.exceptions.DatabaseException;
import br.com.emanuelcosta.enrollment.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public List<Student> findAll(){
		
		return studentRepository.findAll();
		
	}
	
	public Student findById(Long id) {
		Optional<Student> obj = studentRepository.findById(id);
		
		
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	
	
	 public Student createStudent(Long courseId, Student student) {
	    	
	    	
	    	try {
	    		
	    		Course courseEntity = courseRepository.findById(courseId).get();
	    		
	    		student.addCourse(courseEntity);
	    	
				
				return studentRepository.save(student);
				
			} catch (EntityNotFoundException e) {
				throw new ResourceNotFoundException(courseId);
			}	
	    		
		}
	
	public Student insert(Student obj) {
		
	    return studentRepository.save(obj);
		
	}
	
	public void delete(Long id) {
		
		
			
			try {
				studentRepository.deleteById(id);
			} catch (EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException(id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException(e.getMessage());
			}
			
		}
	
	public Student update(Long id,  Student obj) {
		
		try {
			Student entity = studentRepository.getReferenceById(id);
			
			entity.setName(obj.getName());
			entity.setEmail(obj.getEmail()); 
			
			
			return studentRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
		
		
	}
	

}

