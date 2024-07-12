package br.com.emanuelcosta.enrollment.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_course")
public class Course implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	
	@JsonIgnore
	@ManyToMany(mappedBy = "courses")
	private Set<Student> students = new HashSet<>();
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "course")
	private List<Instructor> instructors = new ArrayList<>();
	
	
	public Course() {
		
	}
		

	public Course(Long id, String name, Set<Student> students, List<Instructor> instructors) {
		super();
		this.id = id;
		this.name = name;
		this.students = students;
		this.instructors = instructors;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}


	public List<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}




	public void addStudent(Student theStudent) {
			
			
			students.add(theStudent);
			
		}
	
	 public void removeStudent(Student theStudent) {
		    this.students.remove(theStudent);
		    theStudent.getCourses().remove(this);
	        
	    }



	@Override
	public int hashCode() {
		return Objects.hash(id, instructors, name, students);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(id, other.id) && Objects.equals(instructors, other.instructors)
				&& Objects.equals(name, other.name) && Objects.equals(students, other.students);
	}  



}
