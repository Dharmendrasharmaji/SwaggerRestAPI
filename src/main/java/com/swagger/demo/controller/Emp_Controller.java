package com.swagger.demo.controller;

import com.swagger.demo.entity.Emp_Entity;
import com.swagger.demo.service.Emp_Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@Api(tags = "Employee Controller",value = "controller for Employee",description = "find required endpoints for Employee")
public class Emp_Controller {

    @Autowired
    private Emp_Service emp_service;

    @GetMapping
    @ApiOperation(value = "Get Employees data with or without Query params")
    public ResponseEntity<?> getAllEmployees(@RequestParam(value = "designation",required = false) String empDesignation,@RequestParam(value = "name",required = false) String empName) {
        List<Emp_Entity> Employees = emp_service.getEmployees(empDesignation,empName);
        return new ResponseEntity<>(Employees, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    @ApiOperation(value = "Get Employee data by Email")
    public ResponseEntity<?> getEmployeeByEmail(@PathVariable("email") String email ) {
        Emp_Entity employeeByEmail = emp_service.getEmployeeByEmail(email);
        return new ResponseEntity<>(employeeByEmail, HttpStatus.OK);

    }

    @PostMapping
    @ApiOperation(value = "Add Employee data to DB")
    public ResponseEntity<?> addEmployee(@RequestBody Emp_Entity employeeEntity) {
        if (employeeEntity == null) {
            return new ResponseEntity<>("Employee data wasn't provided", HttpStatus.BAD_REQUEST);
        } else {
            Emp_Entity emp_entity = emp_service.addNewEmployee(employeeEntity);
            return new ResponseEntity<>(emp_entity, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{email}")
    @ApiOperation(value = "Delete Employee data by Email")
    public ResponseEntity<?> deleteEmployeeByEmail(@PathVariable String email) {
        Emp_Entity emp_entity = emp_service.deleteEmployeeByEmail(email);
        return new ResponseEntity<>(emp_entity, HttpStatus.ACCEPTED);
    }

}
