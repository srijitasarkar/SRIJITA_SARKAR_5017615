package com.example.employeemanagementsystem5.repository;

import com.example.employeemanagementsystem5.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Derived query method to find employees by department
    List<Employee> findByDepartmentId(Long departmentId);

    // Derived query method to find employees by name
    List<Employee> findByNameContaining(String name);

    // Find employees by department name
    List<Employee> findByDepartmentName(String departmentName);

    // Find employees by email domain
    List<Employee> findByEmailEndingWith(String domain);

    // Find employees by partial name match
    List<Employee> findByNameContainingIgnoreCase(String name);

    // Find employees by department ID sorted by name
    List<Employee> findByDepartmentIdOrderByNameAsc(Long departmentId);


    // Custom query to find employees by department name using JPQL
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);

    // Custom query to find employees by email domain using native SQL
    @Query(value = "SELECT * FROM employees e WHERE e.email LIKE %:domain", nativeQuery = true)
    List<Employee> findEmployeesByEmailDomain(@Param("domain") String domain);

    // Custom query to find employees with a specific name pattern
    @Query("SELECT e FROM Employee e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employee> searchEmployeesByNamePattern(@Param("name") String name);

    // Execute a named query to find employees by department name
    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

    // Execute a named query to find employees by email domain
    List<Employee> findByEmailDomain(@Param("domain") String domain);
}
