package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import main.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("from Customer cus where cus.userId = :id")
	public Customer getByUserId(@Param("id") long id);

}
