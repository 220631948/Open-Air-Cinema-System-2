package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Order;

import java.util.Date;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {

}
