package com.udacity.jdnd.course3.critter.schedule.service;

import com.udacity.jdnd.course3.critter.pet.entity.Pet;
import com.udacity.jdnd.course3.critter.schedule.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getSchedules(){
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Long scheduleId){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        Schedule schedule = new Schedule();
        if(optionalSchedule.isPresent()){
            schedule = optionalSchedule.get();
        }
        return schedule;
    }

    public List<Schedule> getPetSchedulesByPetId(Long id){
        return scheduleRepository.getSchedulesByPets_Id(id);
    }

    public List<Schedule> getPetSchedulesByEmployeeId(Long id){
        return scheduleRepository.getSchedulesByEmployee_Id(id);
    }

    public List<Schedule> getPetSchedulesByCustomerId(Long id){
        return scheduleRepository.getSchedulesByCustomer_Id(id);
    }



}
