package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.dao.IOrderDetailsRepository;
import by.teachmeskills.eshop.dao.IUserRepository;
import by.teachmeskills.eshop.domain.entities.OrderDetails;
import by.teachmeskills.eshop.domain.entities.OrderDetailsId;
import by.teachmeskills.eshop.domain.entities.User;
import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.dto.converters.UserConverter;
import by.teachmeskills.eshop.services.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userDao;
    private final IOrderDetailsRepository orderDetailsDao;
    private final UserConverter userConverter;

    @Override
    public ResponseEntity<UserDto> login(User user) throws Exception {
        user = userDao.getUserByLoginAndPassword(user.getUsername(), user.getPassword());
        if (user != null) {
            return new ResponseEntity<>(userConverter.toDto(user), HttpStatus.OK);
        } else {
            log.info("Authorization needed.");
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<UserDto> registration(User user) throws Exception {
        if (userDao.checkUserByUsername(user.getUsername())) {
            userDao.create(user);
            user = userDao.getUserByLoginAndPassword(user.getUsername(), user.getPassword());
            log.info("New user was created!");
            return new ResponseEntity<>(userConverter.toDto(user), HttpStatus.OK);
        }
        log.info("User with this name exists.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<UserDto> getUserDataForMyPage(User user) throws Exception {
        user = userDao.getUserByLoginAndPassword("admin", "admin"); //чтобы был юзер в сессии
        if (user != null && isAuthenticated(user)) {
            List<OrderDetailsId> orders = orderDetailsDao.getOrderDetails(user).stream()
                    .map(OrderDetails::getOrderDetailsId)
                    .toList();
            return new ResponseEntity<>(userConverter.toDto(user), HttpStatus.OK);
        } else {
            log.info("Authorization needed.");
            return new ResponseEntity<>(userConverter.toDto(user), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public boolean isAuthenticated(User user) throws Exception {
        return user != null && userDao.checkUser(user);
    }
}
