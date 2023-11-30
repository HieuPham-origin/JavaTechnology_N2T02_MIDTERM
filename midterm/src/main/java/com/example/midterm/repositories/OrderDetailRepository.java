package com.example.midterm.repositories;


import com.example.midterm.dtos.ProductDTO;
import com.example.midterm.models.OrderDetail;
import com.example.midterm.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT od FROM OrderDetail od WHERE od.order.orderId = ?1 and od.product.productId = ?2")
    public Optional<OrderDetail> existOrder(int orderId, int productId);
    @Query("SELECT new com.example.midterm.dtos.ProductDTO(P.product, P.price, P.quantity) FROM OrderDetail P WHERE P.order.orderId = ?1")
    public List<ProductDTO> listItems(int orderId);
    @Query("SELECT od FROM OrderDetail od where od.product.productId = ?1 and od.order.orderId = ?2")
    public OrderDetail getItem(int shoesId, int orderId);
    @Query("SELECT sum(s.price) FROM OrderDetail s WHERE s.order.orderId = ?1")
    public Integer total(int orderId);
}
