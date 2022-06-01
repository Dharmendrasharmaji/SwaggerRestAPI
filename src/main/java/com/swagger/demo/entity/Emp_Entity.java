package com.swagger.demo.entity;


import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "Employee_Details")
public class Emp_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer empID;
    private String empName;
    private String empEmail;
    private String empDesignation;
    private Date empJoiningDate;
    private String empMobileNum;


}
