package by.teachmeskills.eshop.dto.converters;

import by.teachmeskills.eshop.dao.IUserRepository;
import by.teachmeskills.eshop.domain.entities.User;
import by.teachmeskills.eshop.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserConverter {

    private IUserRepository userDao;

    public UserDto toDto (User user) {
     return UserDto.builder()
             .id(user.getId())
             .img(user.getImg())
             .name(user.getName())
             .birthday(user.getBirthday())
             .email(user.getEmail())
             .info(user.getInfo())
             .surname(user.getSurname())
             .username(user.getUsername())
             .build();
    }

    public User toEntity (UserDto userDto) {
        return userDao.findById(userDto.getId());
    }
}
