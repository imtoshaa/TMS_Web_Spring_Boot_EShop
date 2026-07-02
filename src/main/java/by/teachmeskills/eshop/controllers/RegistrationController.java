package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.domain.entities.User;
import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.services.IUserService;
import by.teachmeskills.eshop.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.eshop.utils.PagesPathEnum.REGISTRATION_PAGE;
import static by.teachmeskills.eshop.utils.PagesPathEnum.SIGN_IN_PAGE;
import static by.teachmeskills.eshop.utils.EshopConstants.USER;

@Slf4j
@RestController
@SessionAttributes({USER})
@RequestMapping("/registration")
@Tag(name = "Registration", description = "The registration API")
public class RegistrationController {

    private final IUserService userService;

    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Registration new user.",
            description = "Registration new user."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User is registered.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User error."
            )
    })
    @PostMapping
    public ResponseEntity<UserDto> registration(@ModelAttribute(USER) User user) throws Exception {
        return userService.registration(user);
    }

    @ModelAttribute(USER)
    public User setUpUserForm() {
        return new User();
    }
}
