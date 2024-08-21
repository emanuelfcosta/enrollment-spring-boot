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

import br.com.emanuelcosta.enrollment.entities.Department;
import br.com.emanuelcosta.enrollment.entities.Instructor;
import br.com.emanuelcosta.enrollment.services.DepartmentService;


@RestController
@RequestMapping(value = "/departments")
public class DepartmentResource {
	
	
	
	@Autowired
	private DepartmentService departimentService;
	
	@GetMapping
	public ResponseEntity<List<Department>> findAll(){
		
	
		
		List<Department> list = departimentService.findAll();
		
		
		
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Department> findById(@PathVariable Long id) {
		Department obj = departimentService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/instructors/department/{departmentId}")
	public ResponseEntity<List<Instructor>> findAllInstructorsByDepartmentId (@PathVariable Long departmentId) {
		List<Instructor> list = departimentService.findAllInstructorsByDepartmentId(departmentId);
		
		return ResponseEntity.ok().body(list);
		
	}
	
	
	
	
	@PostMapping
	public ResponseEntity<Department> insert(@RequestBody Department obj){
		
		obj = departimentService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id){
		
		departimentService.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Department> update(@PathVariable Long id, @RequestBody Department obj){
		
		obj = departimentService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	

}
