package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.CustomerComment;

@Repository
public interface CustomerCommentRepository extends JpaRepository<CustomerComment, Long> {

}
