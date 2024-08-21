package br.com.emanuelcosta.enrollment.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;

@Entity
@Table(name = "tb_student")
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	
	
	
	@ManyToMany( 
		 cascade = {
					
			CascadeType.MERGE,
			CascadeType.DETACH,
			CascadeType.REFRESH 
			}
)
	@JoinTable(name = "tb_student_course", 
	joinColumns = @JoinColumn(name="student_id"),
	inverseJoinColumns = @JoinColumn(name="course_id"))
	private Set<Course> courses = new HashSet<>();
	

	public Student() {
		
	}

	public Student(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Course> getCourses() {
		//return courses;
		//force clients through our add and remove methods
		return Collections.unmodifiableSet(courses);
	}
	
	 public void setCourses(Set<Course> course) {
		    this.courses = course;
	}
	 
		public void addCourse(Course theCourse) {
			
			courses.add(theCourse);
		
		
	}  
	
		public void removeCourse(Course theCourse) {
			
			courses.remove(theCourse);
	
		
		
	}		
	 
 	
	@Override
	public int hashCode() {
		return Objects.hash(courses, email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(courses, other.courses) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}



}

