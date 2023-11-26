package com.example.midterm.repositories;


import com.example.midterm.models.OrderDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    void deleteById(int id);
}
