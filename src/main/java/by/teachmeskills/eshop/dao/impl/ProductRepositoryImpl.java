package by.teachmeskills.eshop.dao.impl;

import by.teachmeskills.eshop.dao.IProductRepository;
import by.teachmeskills.eshop.domain.entities.Product;
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
public class ProductRepositoryImpl implements IProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Product product) throws Exception {
        entityManager.persist(product);
    }

    @Override
    public List<Product> read() throws Exception {
        return entityManager.createQuery("select r from Order r ").getResultList();
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) throws Exception {
        Query query = entityManager.createQuery("select p from Product p where p.category.id=:categoryId");
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }

    @Override
    public Product getProductById(int productId) throws Exception {
        Query query = entityManager.createQuery("select p from Product p where p.id=:productId");
        query.setParameter("productId", productId);
        return (Product) query.getSingleResult();
    }

}
