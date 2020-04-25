package com.example.demo.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

import javax.persistence.QueryHint;

import static org.hibernate.annotations.QueryHints.READ_ONLY;
import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;
import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@QueryHints(value = {
			@QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE),
			@QueryHint(name = HINT_CACHEABLE, value = "false"),
			@QueryHint(name = READ_ONLY, value = "true")
	})
	@Query("select e from Employee e")
	Stream<Employee> streamAllEmployees();
	
}
