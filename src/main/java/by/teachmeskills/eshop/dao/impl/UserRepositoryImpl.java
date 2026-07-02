package by.teachmeskills.eshop.dao.impl;

import by.teachmeskills.eshop.dao.IUserRepository;
import by.teachmeskills.eshop.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Repository
@Transactional
public class UserRepositoryImpl implements IUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) throws Exception {
        entityManager.persist(user);
    }

    @Override
    public List<User> read() throws Exception {
        return entityManager.createQuery("select с from Category с ").getResultList();
    }

    @Override
    public User getUserByLoginAndPassword(String username, String pass) throws Exception {
        Query query = entityManager.createQuery("select u from User u where u.username=:username and u.password=:password");
        query.setParameter("username", username);
        query.setParameter("password", pass);
        return (User) query.getSingleResult();
    }

    @Override
    public boolean checkUser(User checkedUser) throws Exception {
        try {
            Query query =  entityManager.createQuery("select u from User u where u.username=:username and u.password=:password");
            query.setParameter("username", checkedUser.getUsername());
            query.setParameter("password", checkedUser.getPassword());
            User user = (User) query.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUserByUsername(String username) throws Exception {
        try {
            Query query =  entityManager.createQuery("select u from User u where u.username=:username");
            query.setParameter("username", username);
            User user = (User) query.getSingleResult();
        } catch (NoResultException e) {
            return true;
        }
        return false;
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }
}
