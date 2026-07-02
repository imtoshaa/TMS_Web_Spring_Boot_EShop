package by.teachmeskills.eshop.dao.impl;

import by.teachmeskills.eshop.dao.IOrderDetailsRepository;
import by.teachmeskills.eshop.domain.entities.OrderDetails;
import by.teachmeskills.eshop.domain.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Repository
@Transactional
public class OrderDetailsRepositorympl implements IOrderDetailsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveOrderDetails(OrderDetails orderDetails) {
        entityManager.persist(orderDetails);
    }

    @Override
    public List<OrderDetails> getOrderDetails(User user) {
        Query getOrders = entityManager.createQuery(
                "select u from OrderDetails u where u.orderDetailsId.order.user.id=:userId");
        getOrders.setParameter("userId", user.getId());
        return getOrders.getResultList();
    }
}

