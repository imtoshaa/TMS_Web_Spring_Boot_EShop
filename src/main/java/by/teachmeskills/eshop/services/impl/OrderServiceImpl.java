package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.dao.IOrderRepository;
import by.teachmeskills.eshop.dao.IOrderDetailsRepository;
import by.teachmeskills.eshop.domain.entities.Order;
import by.teachmeskills.eshop.domain.entities.OrderDetails;
import by.teachmeskills.eshop.domain.entities.OrderDetailsId;
import by.teachmeskills.eshop.domain.entities.Product;
import by.teachmeskills.eshop.services.IOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderDao;
    private final IOrderDetailsRepository orderDetailsDao;

    @Override
    public Set<Order> getUserOrderById(int userId) throws Exception {
        log.info("Receiving user id=" + userId + " orders in the process");
        return orderDao.getUserOrdersById(userId);
    }

    @Override
    public void create(Order order, Product product) throws Exception {
        orderDao.create(order);
        OrderDetailsId orderDetailsId = OrderDetailsId.builder()
                .product(product)
                .order(order)
                .build();
        OrderDetails orderDetails = OrderDetails.builder()
                .orderDetailsId(orderDetailsId)
                .build();
        orderDetailsDao.saveOrderDetails(orderDetails);
        log.info("Order has been created.");
    }


}
