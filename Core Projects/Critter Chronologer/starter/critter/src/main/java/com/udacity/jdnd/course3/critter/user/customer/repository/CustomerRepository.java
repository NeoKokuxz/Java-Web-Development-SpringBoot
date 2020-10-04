package com.udacity.jdnd.course3.critter.user.customer.repository;

import com.udacity.jdnd.course3.critter.user.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
