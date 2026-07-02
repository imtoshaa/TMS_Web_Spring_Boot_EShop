package by.teachmeskills.eshop.dao;

import by.teachmeskills.eshop.domain.entities.User;

public interface IUserRepository extends BaseRepository<User> {
    User getUserByLoginAndPassword(String username, String pass) throws Exception;
    boolean checkUser(User checkedUser) throws Exception;
    boolean checkUserByUsername(String username) throws Exception;
    User findById (int id);
}
