package by.teachmeskills.eshop.dao.impl;

import by.teachmeskills.eshop.dao.ICategoryRepository;
import by.teachmeskills.eshop.domain.entities.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Repository
@Transactional
public class CategoryRepositoryImpl implements ICategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Category category) throws Exception {
        entityManager.persist(category);
    }

    @Override
    public List<Category> read() throws Exception {
        return entityManager.createQuery("select с from Category с ").getResultList();
    }
}
