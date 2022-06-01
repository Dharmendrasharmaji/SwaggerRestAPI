package com.swagger.demo.service;

import com.swagger.demo.entity.Emp_Entity;
import com.swagger.demo.exceptions.EmployeeAlreadyExistsException;
import com.swagger.demo.exceptions.EmployeeNotFoundException;
import com.swagger.demo.exceptions.NoEmployeeDataFoundException;
import com.swagger.demo.repository.Emp_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Emp_Service_Impl implements Emp_Service{

    @Autowired
    private Emp_Repository emp_repository;

    @Override
    public List<Emp_Entity> getEmployees(String empDesignation,String empName) throws NoEmployeeDataFoundException {

        if (empDesignation!=null && empName!=null){

            Optional<List<Emp_Entity>> byEmployeeDesignationAndEmpName = emp_repository.findByEmpDesignationAndEmpName(empDesignation, empName);
            if (byEmployeeDesignationAndEmpName.get().isEmpty()){
                return getAllEmployees();
            }
            return byEmployeeDesignationAndEmpName.get();

        }else if (empDesignation!=null && empName==null){

            Optional<List<Emp_Entity>> byEmployeeDesignation = emp_repository.findByEmpDesignation(empDesignation);
            if (byEmployeeDesignation.get().isEmpty()){
                return getAllEmployees();
            }
            return byEmployeeDesignation.get();

        }else if (empDesignation==null && empName!=null){

            Optional<List<Emp_Entity>> byEmployeeName= emp_repository.findByEmpName(empName);
            if (byEmployeeName.get().isEmpty()){
                return getAllEmployees();
            }
            return byEmployeeName.get();

        }

        return getAllEmployees();
    }

    @Override
    public Emp_Entity getEmployeeByEmail(String email) throws EmployeeNotFoundException {
        Optional<Emp_Entity> byemailId = emp_repository.findByEmpEmail(email);
        return byemailId.orElseThrow(()->new EmployeeNotFoundException("Employee with given email doesn't exist."));
    }

    @Override
    public Emp_Entity addNewEmployee(Emp_Entity employeeEntity) throws EmployeeAlreadyExistsException {
        employeeEntity.setEmpEmail(employeeEntity.getEmpEmail().toUpperCase());
        employeeEntity.setEmpDesignation(employeeEntity.getEmpDesignation().toUpperCase());
        employeeEntity.setEmpName(employeeEntity.getEmpName().toUpperCase());
        Optional<Emp_Entity> byEmployeeEmail = emp_repository.findByEmpEmail(employeeEntity.getEmpEmail());
        if(byEmployeeEmail.isPresent()){
            throw new EmployeeAlreadyExistsException("Employee with given email is already exist.");
        }
        Emp_Entity emp_entity = emp_repository.save(employeeEntity);
        return emp_entity;
    }

    @Override
    public Emp_Entity deleteEmployeeByEmail(String email) throws EmployeeNotFoundException {
        Optional<Emp_Entity> byEmployeeEmail = emp_repository.findByEmpEmail(email);
        if(byEmployeeEmail.isEmpty()){
            throw new EmployeeNotFoundException("Employee with given email doesn't exist.");
        }
        emp_repository.delete(byEmployeeEmail.get());
        return byEmployeeEmail.get();
    }

    public List<Emp_Entity> getAllEmployees(){
        List<Emp_Entity> allEmployees = emp_repository.findAll();
        if (allEmployees.isEmpty()){
            throw new NoEmployeeDataFoundException("Employees data are not present.");
        }
        return allEmployees;
    }
}
