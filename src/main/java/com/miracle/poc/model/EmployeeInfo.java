package com.miracle.poc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
@Entity
public class EmployeeInfo {

    @Id
    @Column(name="empid")
    private int empid ;
    @Column(name="empname")
    private String empname;
    @Column(name="empphone")
    private String empphone;
    @Column(name="empemail")
    private String empemail;
    @Column(name="empstatus")
    private String empstatus;
    @Column(name="empaddress")
    private String empaddress;
    @Column(name="emprole")
    private String emprole;
    @Column(name="empskill")
    private String empskill;


}
