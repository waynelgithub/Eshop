package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.model.ShoppingCartDetails;

@Repository
public interface ShoppingCartDetailsRepository extends JpaRepository<ShoppingCartDetails, Long> {
	
	@Modifying
	@Query("delete from ShoppingCartDetails s where s.id = :id")
	public void deleteByIdWithShoppingCartDetails(@Param("id") long id);

	@Query("from ShoppingCartDetails s where s.productNum = :productNum and s.shoppingCart.id = :id")
	public ShoppingCartDetails getByProductNum(@Param("productNum") long productNum, @Param("id") long id);
}
