package com.swagger.demo.service;

import com.swagger.demo.entity.Emp_Entity;
import com.swagger.demo.exceptions.EmployeeAlreadyExistsException;
import com.swagger.demo.exceptions.EmployeeNotFoundException;
import com.swagger.demo.exceptions.NoEmployeeDataFoundException;

import java.util.List;

public interface Emp_Service {

    List<Emp_Entity> getEmployees(String empDesignation,String empName) throws NoEmployeeDataFoundException;
    Emp_Entity getEmployeeByEmail(String email) throws EmployeeNotFoundException;
    Emp_Entity addNewEmployee(Emp_Entity employeeEntity) throws EmployeeAlreadyExistsException;
    Emp_Entity deleteEmployeeByEmail(String email) throws EmployeeNotFoundException;
}
