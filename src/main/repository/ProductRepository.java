package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	  
	  //find distinct product types
	  @Query("select DISTINCT p.prodType from product p" )
	  List<String> findDistinctProdType();
	  
	  //find distinct product lines
	  @Query("select DISTINCT p.prodline from product p" )
	  List<String> findDistinctProdline();

}
