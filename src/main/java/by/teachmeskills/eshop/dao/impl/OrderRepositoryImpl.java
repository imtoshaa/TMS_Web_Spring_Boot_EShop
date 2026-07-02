package by.teachmeskills.eshop.dao.impl;

import by.teachmeskills.eshop.dao.IOrderRepository;
import by.teachmeskills.eshop.domain.entities.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Repository
@Transactional
public class OrderRepositoryImpl implements IOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Order order) throws Exception {
        entityManager.persist(order);
    }

    @Override
    public List<Order> read() throws Exception {
        return entityManager.createQuery("select r from Order r ").getResultList();
    }

    @Override
    public Set<Order> getUserOrdersById(int userId) throws Exception {
        Query query = entityManager.createQuery(
                "select o from Order o where o.user.id=:userId");
        query.setParameter("userId", userId);
        return new HashSet<>(query.getResultList());

    }
}
