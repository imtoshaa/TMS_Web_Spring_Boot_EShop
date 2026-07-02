package by.teachmeskills.eshop.dao;

import by.teachmeskills.eshop.domain.entities.OrderDetails;
import by.teachmeskills.eshop.domain.entities.User;

import java.util.List;

public interface IOrderDetailsRepository {
    void saveOrderDetails(OrderDetails orderDetails);

    List<OrderDetails> getOrderDetails(User user);
}
