package br.com.emanuelcosta.enrollment.resources;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.emanuelcosta.enrollment.entities.Course;
import br.com.emanuelcosta.enrollment.entities.Student;
import br.com.emanuelcosta.enrollment.services.StudentService;



@RestController
@RequestMapping(value = "/students")
public class StudentResource {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public ResponseEntity<List<Student>> findAll(){
		
	
		
		List<Student> list = studentService.findAll();
		
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/courses/student/{studentId}")
	public ResponseEntity<Set<Course>> findCoursesByStudentId(@PathVariable Long studentId) {
		Set<Course> list = studentService.findCoursesByStudentId(studentId); 
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Student> findById(@PathVariable Long id) {
		Student obj = studentService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// enroll the student in the course
	@PutMapping(value = "/courses/{courseId}/subscribe")
	public ResponseEntity<Student> subscribe(@PathVariable Long courseId, @RequestBody Student obj){
		
		obj =  studentService.subscribeStudent(courseId, obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	//unenroll student from the course
	@PutMapping(value = "/courses/{courseId}/unsubscribe")
	public ResponseEntity<Student> unsubscribe(@PathVariable Long courseId, @RequestBody Student obj){
		
		  
		
		obj =  studentService.unsubscribeStudent(courseId, obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseId}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
		
		
	}
	

	

	
	
	@PostMapping
	public ResponseEntity<Student> insert(@RequestBody Student obj){
		
		
		obj =  studentService.insert( obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student obj){
		
		obj = studentService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	

	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		
		studentService.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	

}
