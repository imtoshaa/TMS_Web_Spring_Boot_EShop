package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.domain.entities.User;
import by.teachmeskills.eshop.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

public interface IUserService {

    ResponseEntity<UserDto> login(User user) throws Exception;
    ResponseEntity<UserDto> registration(User user) throws Exception;
    ResponseEntity<UserDto> getUserDataForMyPage(User user) throws Exception;
    boolean isAuthenticated(User user) throws Exception;
}
