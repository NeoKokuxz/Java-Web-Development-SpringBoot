package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.user.customer.entity.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.employee.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.employee.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private PetMapper petMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private  EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        List<Long> petIds = customerDTO.getPetIds();
        List<Pet> pets = new ArrayList<>();
        if (petIds != null) {546
            for (Long petId : petIds) {
                Pet pet = petService.getPet(petId);
                pets.add(pet);
            }
        }
        Customer customer = customerMapper.DTOtoEntity(customerDTO);
        customer.setPets(pets);

        Customer savedCustomer = customerService.createCustomer(customer);
        return customerMapper.entityToDTO(savedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customerMapper.entitiesToDTOs(customers);
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        if (pet != null) {
            if (pet.getCustomer() != null) {
                Customer customer = customerService.getCustomer(pet.getCustomer().getId());
                return customerMapper.entityToDTO(customer);
            }
        }
        return null;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.DTOtoEntity(employeeDTO);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return employeeMapper.entityToDTO(savedEmployee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return employeeMapper.entityToDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee != null) {
            employee.setDaysAvailable(daysAvailable);
            employeeService.saveEmployee(employee);
        }
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.getEmployeeByService(employeeDTO.getSkills(), employeeDTO.getDate());
        return employeeMapper.entitiesToDTOs(employees);
    }


}
