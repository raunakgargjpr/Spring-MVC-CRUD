package com.raunak.springboot.thymeleafdemo.dao;

import com.raunak.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!

    // add a method to sort by last name

    // spring data jpa will automatically create query by parsing the function name
    public List<Employee> findAllByOrderByLastNameAsc();
}
