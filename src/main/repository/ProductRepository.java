package main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	  // a way to get product with image
	  @EntityGraph(attributePaths = { "productImage" })
	  Product getById(long id);	  
 
	  //find all products with their images
	  @EntityGraph(attributePaths = { "productImage" })
	  List<Product> findByIdIsNotNull();

}
