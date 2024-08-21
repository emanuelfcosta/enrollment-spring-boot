package br.com.emanuelcosta.enrollment.services;


import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	
	public Set<Course> findCoursesByStudentId(Long id) {
		
		
		return  studentRepository.findCoursesByStudentId(id) ;
		
	}
	
	public Student findById(Long id) {
		Optional<Student> obj = studentRepository.findById(id);
		
		
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	 public Student insert(Student obj) {
	        
	        return studentRepository.save(obj);
	    }
	 
		// subscribe  the student in the course
	 public Student subscribeStudent(Long courseId, Student obj) {
	    	
	    	
	    	try {
	    		
	    		Course courseEntity = courseRepository.findById(courseId).get();
	    		
	    		
	    		
	    		courseEntity.addStudent(obj);
	    		
	    		
	    		
	    	return	studentRepository.save(obj);
	    		
	           
	    	
				
				
				
			} catch (EntityNotFoundException e) {
				throw new ResourceNotFoundException(courseId);
			}	
	    		
		}
	 
	 
	 public Student unsubscribeStudent(Long courseId, Student obj) {
	    	
	    	
	    	try {
	    		
	    		Course courseEntity = courseRepository.findById(courseId).get();
	    	
	    		
	    		
	    		
	           		
	    		studentRepository.deleteStudentCourse(obj.getId(), courseEntity.getId());
	    		
	    		
	    		
	    	return	studentRepository.save(obj);
	    		
	    		
	    		  
	    	         
				
			} catch (EntityNotFoundException e) {
				throw new ResourceNotFoundException(courseId);
			}	
	    		
		}

	
	
	
	 
	 public Student update(Long studentId,  Student obj) {
			
			try {
				
				Student entity = studentRepository.getReferenceById(studentId);
				
				
				
				entity.setName(obj.getName());
				entity.setEmail(obj.getEmail()); 
				
				
				return studentRepository.save(entity);
			} catch (EntityNotFoundException e) {
				throw new ResourceNotFoundException(studentId);
			}		 
	 
	 
			
			
		}
	 
	

	 
	 
	
//	public Student insert(Student obj) {
//		
//	    return studentRepository.save(obj);
//		
//	}
	
	public void delete(Long id) {
		
		
			
			try {
				studentRepository.deleteById(id);
			} catch (EmptyResultDataAccessException e) {
				throw new ResourceNotFoundException(id);
			} catch (DataIntegrityViolationException e) {
				throw new DatabaseException(e.getMessage());
			}
			
		}
	
	

}

