package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	@Query("from ShoppingCart s left join fetch s.shoppingCartDetails where s.id = :id")
	public ShoppingCart getByIdWithShoppingCartDetails(@Param("id") long id);
}
