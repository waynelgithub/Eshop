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
	
	//改 left join, 才能在購物車尚未有購買項目的時候(shoppingCartDetails == null)的時候，也能被撈出來
	@Query("from ShoppingCart s left join fetch s.shoppingCartDetails where s.customer_num = :customer_num")
	public ShoppingCart getByCustomerNum(@Param("customer_num") String customer_num);
		
	List<ShoppingCart> findFirstByOrderById(); 
}
