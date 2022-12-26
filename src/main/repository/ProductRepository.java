package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Product;
/**
 * 
 * @author hsu
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
