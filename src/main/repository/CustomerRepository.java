package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
}
