package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Product;
import main.model.Sorder;

@Repository
public interface SorderRepository extends JpaRepository<Sorder, Long> {

}
