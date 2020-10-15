package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.service.PetService;
import com.udacity.jdnd.course3.critter.user.customer.entity.Customer;
import com.udacity.jdnd.course3.critter.user.customer.entity.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.customer.service.CustomerService;
import com.udacity.jdnd.course3.critter.user.employee.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.employee.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.employee.entity.Employee;
import com.udacity.jdnd.course3.critter.user.employee.entity.EmployeeRequest;
import com.udacity.jdnd.course3.critter.user.employee.service.EmployeeService;
import org.springframework.beans.BeanUtils;
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
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private  EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        List<Long> petIds = customerDTO.getPetIds();
        List<Pet> pets = new ArrayList<>();
        if (petIds != null) {
            for (Long petId : petIds) {
                Pet pet = petService.getPetById(petId);
                pets.add(pet);
            }
        }
        Customer customer = convertCustomerDTOToEntity(customerDTO);
        customer.setPets(pets);

        Customer savedCustomer = customerService.saveCustomer(customer);
        return convertEntityToCustomerDTO(savedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return convertEntityListToCustomerDTOList(customers);
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        if (pet != null) {
            if (pet.getCustomer() != null) {
                Customer customer = customerService.getCustomerById(pet.getCustomer().getId());
                convertEntityToCustomerDTO(customer);
            }
        }
        return null;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.saveEmployee(convertEmployeeDTOToEntity(employeeDTO));
        return convertEntityToEmployeeDTO(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return convertEntityToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            employee.setDaysAvailable(daysAvailable);
            employeeService.saveEmployee(employee);
        }
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        EmployeeRequest request = convertRequest(employeeDTO);
        List<Employee> employees = employeeService.findEmployeesForService(request);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employees.forEach(employee -> employeeDTOS.add(convertEntityToEmployeeDTO(employee)));
        return employeeDTOS;
    }


    //Convert methods below
    private static EmployeeRequest convertRequest(EmployeeRequestDTO requestDTO) {
        EmployeeRequest request = new EmployeeRequest();
        BeanUtils.copyProperties(requestDTO, request);
        return request;
    }

    private static List<CustomerDTO> convertEntityListToCustomerDTOList(List<Customer> customerList){
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerList.forEach(customer -> customerDTOList.add(convertEntityToCustomerDTO(customer)));
        return customerDTOList;
    }

    private static List<EmployeeDTO> convertEntityListToEmployeeDTOList(List<Employee> employeeList){
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeList.forEach(employee -> employeeDTOList.add(convertEntityToEmployeeDTO(employee)));
        return employeeDTOList;
    }

    private static EmployeeDTO convertEntityToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private static Employee convertEmployeeDTOToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    private static CustomerDTO convertEntityToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);

        if (customer.getPets() != null && customer.getPets().size() > 0) {
            List<Long> petIds = new ArrayList<>();
            customer.getPets().forEach(pet -> petIds.add(pet.getId()));
            customerDTO.setPetIds(petIds);
        }
        return customerDTO;
    }

    private static Customer convertCustomerDTOToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);

        if (customerDTO.getPetIds() != null && customerDTO.getPetIds().size() > 0) {
            List<Pet> pets = new ArrayList<>();
            customerDTO.getPetIds().forEach(petId -> {
                Pet pet = new Pet();
                pet.setId(petId);
                pets.add(pet);
            });
            customer.setPets(pets);
        }
        return customer;
    }

//    private static EmployeeRequest convertRequestDTOToRequest(EmployeeRequestDTO requestDTO) {
//        EmployeeRequest request = new EmployeeRequest();
//        BeanUtils.copyProperties(requestDTO, request);
//        return request;
//    }

}
