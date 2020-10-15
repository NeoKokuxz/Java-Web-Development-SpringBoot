package com.udacity.jdnd.course3.critter.user.employee.service;

import com.udacity.jdnd.course3.critter.user.employee.entity.Employee;
import com.udacity.jdnd.course3.critter.user.employee.entity.EmployeeRequest;
import com.udacity.jdnd.course3.critter.user.employee.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployeeList(){
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = new Employee();
        if(optionalEmployee.isPresent()){
            employee = optionalEmployee.get();
        }
        return employee;
    }

    public void setAvailability(Set<DayOfWeek> days, Long id){
        Employee employee = getEmployeeById(id);
        employee.setDaysAvailable(days);
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(EmployeeRequest request) {
        Set<EmployeeSkill> skills = request.getSkills();
        DayOfWeek date = request.getDate().getDayOfWeek();

        List<Employee> allEmployees = employeeRepository.findAll();
        List<Employee> availableEmployees = new ArrayList<>();
        for (Employee employee : allEmployees) {
            if (employee.getDaysAvailable().contains(date) && employee.getSkills().containsAll(skills)) {
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }
}
