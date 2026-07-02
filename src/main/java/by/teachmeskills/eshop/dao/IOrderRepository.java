package by.teachmeskills.eshop.dao;

import by.teachmeskills.eshop.domain.entities.Order;

import java.util.Set;

public interface IOrderRepository extends BaseRepository<Order> {

     Set<Order> getUserOrdersById(int userId) throws Exception;
}
