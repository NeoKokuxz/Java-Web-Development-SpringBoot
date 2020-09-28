package com.udacity.jdnd.course3.critter.schedule.entity;

import com.udacity.jdnd.course3.critter.user.employee.entity.Employee;
import com.udacity.jdnd.course3.critter.user.employee.entity.EmployeeSkill;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private List<Employee> employee;

    @ManyToMany
    private List<Schedule> scheduleList;

    @ElementCollection
    private Set<EmployeeSkill> employeeSkillSet;

    public Schedule() {
    }

    public Schedule(Long id, List<Employee> employee, List<Schedule> scheduleList, Set<EmployeeSkill> employeeSkillSet) {
        this.id = id;
        this.employee = employee;
        this.scheduleList = scheduleList;
        this.employeeSkillSet = employeeSkillSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public Set<EmployeeSkill> getEmployeeSkillSet() {
        return employeeSkillSet;
    }

    public void setEmployeeSkillSet(Set<EmployeeSkill> employeeSkillSet) {
        this.employeeSkillSet = employeeSkillSet;
    }
}
