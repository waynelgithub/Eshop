package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
