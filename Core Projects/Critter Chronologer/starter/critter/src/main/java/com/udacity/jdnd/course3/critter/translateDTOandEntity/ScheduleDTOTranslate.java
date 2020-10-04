package com.udacity.jdnd.course3.critter.translateDTOandEntity;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.schedule.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.user.employee.entity.Employee;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ScheduleDTOTranslate {
    private static Schedule convertScheduleDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        if (scheduleDTO.getEmployeeIds() != null && scheduleDTO.getEmployeeIds().size() > 0) {
            List<Employee> employees = new ArrayList<>();

            scheduleDTO.getEmployeeIds().forEach(id -> {
                Employee employee = new Employee();
                employee.setId(id);
                employees.add(employee);
            });
            schedule.setEmployees(employees);
        }

        if (scheduleDTO.getPetIds() != null && scheduleDTO.getEmployeeIds().size() > 0) {
            List<Pet> pets = new ArrayList<>();

            scheduleDTO.getPetIds().forEach(id -> {
                Pet pet = new Pet();
                pet.setId(id);
                pets.add(pet);
            });
            schedule.setPets(pets);
        }
        return schedule;
    }

    private static ScheduleDTO convertEntityToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        if (schedule.getEmployees() != null && schedule.getEmployees().size() > 0) {
            List<Long> employeeIds = new ArrayList<>();

            schedule.getEmployees().forEach(employee -> {
                employeeIds.add(employee.getId());
                scheduleDTO.setEmployeeIds(employeeIds);
            });
        }

        if (schedule.getPets() != null && schedule.getPets().size() > 0) {
            List<Long> petIds = new ArrayList<>();

            schedule.getPets().forEach(pet -> {
                petIds.add(pet.getId());
                scheduleDTO.setPetIds(petIds);
            });
        }

        return scheduleDTO;
    }
}
