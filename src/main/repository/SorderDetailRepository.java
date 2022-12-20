package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.model.SorderDetail;

@Repository
public interface SorderDetailRepository extends JpaRepository<SorderDetail, Long> {

}
