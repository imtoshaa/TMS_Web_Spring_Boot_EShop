package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.domain.entities.User;
import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.services.IUserService;
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

import static by.teachmeskills.eshop.utils.PagesPathEnum.SIGN_IN_PAGE;
import static by.teachmeskills.eshop.utils.EshopConstants.USER;

@Slf4j
@RestController
@SessionAttributes({USER})
@Tag(name = "Auth", description = "The auth API")
@RequestMapping("/login")
public class AuthController {

    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Login and open home page",
            description = "Login and open home page"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Logged in",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - unauthorized"
            )
    })
    @PostMapping
    public ResponseEntity<UserDto> login(@ModelAttribute(USER) User user) throws Exception {
        return userService.login(user);
    }

    @ModelAttribute(USER)
    public User setUpUserForm() {
        return new User();
    }
}
