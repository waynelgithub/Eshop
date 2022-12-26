package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.model.ShoppingCart;
import main.model.Tour;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

//	@Query("from ShoppingCart s left join fetch s.comments where t.id = :id")
//	public Tour getByIdWithComments(@Param("id") long id);
}
