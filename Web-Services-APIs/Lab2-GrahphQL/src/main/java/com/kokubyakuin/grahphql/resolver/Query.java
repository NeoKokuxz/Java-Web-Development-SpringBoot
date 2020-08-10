package com.kokubyakuin.grahphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.kokubyakuin.grahphql.entity.Location;
import com.kokubyakuin.grahphql.repository.LocationRepository;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLResolver {
    private LocationRepository locationRepository;

    public Query(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Iterable<Location> findAllLocations() {
        return locationRepository.findAll();
    }
}
