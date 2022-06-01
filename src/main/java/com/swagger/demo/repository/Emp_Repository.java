package com.swagger.demo.repository;

import com.swagger.demo.entity.Emp_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Emp_Repository extends JpaRepository<Emp_Entity,Integer> {

    Optional<Emp_Entity> findByEmpEmail(String email);
    Optional<List<Emp_Entity>> findByEmpDesignationAndEmpName(String empDesignation,String empName);
    Optional<List<Emp_Entity>> findByEmpDesignation(String empDesignation);
    Optional<List<Emp_Entity>> findByEmpName(String empName);
}
