package com.kokubyakuin.grahphql.service;

import com.kokubyakuin.grahphql.entity.Location;
import com.kokubyakuin.grahphql.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceIMP implements LocationService {
    @Autowired
    LocationRepository locationRepository;

    public List<Location> retrieveLocations() {
        return (List<Location>) locationRepository.findAll();
    }
}
