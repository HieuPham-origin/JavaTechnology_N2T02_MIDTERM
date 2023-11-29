package com.example.midterm.repositories;


import com.example.midterm.models.OrderDetail;
import com.example.midterm.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    public Optional<OrderDetail> findByOrderOrderIdAndProductProductId(int orderId, int productId);

    public List<Product> findByOrderOrderId(int orderId);

    public OrderDetail findByProductProductIdAndOrderOrderId(int shoesId, int orderId);

//    public Integer sumPriceByOrderOrderId(int orderId);
}
