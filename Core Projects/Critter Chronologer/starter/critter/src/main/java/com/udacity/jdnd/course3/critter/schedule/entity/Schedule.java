package com.udacity.jdnd.course3.critter.schedule.entity;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.user.employee.entity.Employee;
import com.udacity.jdnd.course3.critter.user.employee.entity.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    // Table generated in MySQL as 'schedule_employees'
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "employee_id"))  // renamed from MySQL default "employee_ids"
    private List<Employee> employees;

    @ManyToMany
    private List<Schedule> scheduleList;

    @ElementCollection
    private Set<EmployeeSkill> employeeSkillSet;

    // Table generated in MySQL as 'schedule pets'
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "pet_id"))  // renamed from MySQL default "pet_ids"
    private List<Pet> pets;

    @Column(name = "activity")  // renamed from MySQL default "activities"
    @ElementCollection
    private Set<EmployeeSkill> activities;

    private LocalDate date;

    public Schedule() {
    }

    public Schedule(long id, List<Employee> employees, List<Pet> pets, LocalDate date, Set<EmployeeSkill> activities) {
        this.id = id;
        this.employees = employees;
        this.pets = pets;
        this.date = date;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}
