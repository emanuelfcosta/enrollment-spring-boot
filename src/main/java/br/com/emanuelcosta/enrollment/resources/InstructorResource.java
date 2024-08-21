package br.com.emanuelcosta.enrollment.resources;

import java.net.URI;
import java.util.List;

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

import br.com.emanuelcosta.enrollment.entities.Instructor;
import br.com.emanuelcosta.enrollment.services.InstructorService;



@RestController
@RequestMapping(value = "/instructors")
public class InstructorResource {
	
	@Autowired
	private InstructorService instructorService;
	

	
	@GetMapping
	public ResponseEntity<List<Instructor>> findAll(){
		

		
		List<Instructor> list = instructorService.findAll();
		
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Instructor> findById(@PathVariable Long id) {
		Instructor obj = instructorService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/departments/{departmentId}/instructors")
	public ResponseEntity<List<Instructor>> findByDepartmentId(@PathVariable Long departmentId){
		
		List<Instructor> list = instructorService.findByDepartmentId(departmentId);
		
		return ResponseEntity.ok().body(list);
		
		
	}
	
	
	
	@PostMapping(value = "/departments/{departmentId}/courses/{courseId}/instructor")
	public ResponseEntity<Instructor> insert(@PathVariable Long departmentId, 
			@PathVariable Long courseId ,@RequestBody Instructor obj){
		
		obj = instructorService.createInstructor(departmentId,courseId, obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	
	@PutMapping(value = "/instructors/{instructorId}/departments/{departmentId}/courses/{courseId}")
	public ResponseEntity<Instructor> update(@PathVariable Long instructorId,
			@PathVariable Long departmentId, @PathVariable Long courseId, 
			@RequestBody Instructor obj){
		
		obj = instructorService.updateInstructor(instructorId,departmentId,courseId, obj);
		return ResponseEntity.ok().body(obj);
		

	}
	

	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		
		instructorService.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	


}


