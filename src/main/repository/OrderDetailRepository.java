package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.model.OrderDetail;
/**
 * 
 * @author hsu
 *
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
