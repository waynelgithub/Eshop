package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	@Query("from ShoppingCart s left join fetch s.shoppingCartDetails where s.id = :id")
	public ShoppingCart getByIdWithShoppingCartDetails(@Param("id") long id);
	
	@Query("from ShoppingCart s join fetch s.shoppingCartDetails where s.customer_num = :id")
	public ShoppingCart getByCustomerNum(@Param("id") long id);
		
	List<ShoppingCart> findFirstByOrderById(); 
}
