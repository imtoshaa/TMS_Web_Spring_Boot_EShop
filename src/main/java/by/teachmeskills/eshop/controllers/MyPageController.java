package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.domain.entities.User;
import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static by.teachmeskills.eshop.utils.EshopConstants.USER;

@Slf4j
@RestController
@SessionAttributes({USER})
@RequestMapping("/mypage")
@AllArgsConstructor
@Tag(name = "User Page", description = "The userPage API")
public class MyPageController {

    private IUserService userService;

    @Operation(
            summary = "Open user page.",
            description = "Open user page."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User page is opened.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User error."
            )
    })
    @GetMapping("/open")
    public ResponseEntity<UserDto> openMyPage(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
        }
        log.info("Redirect to my page.");
        return userService.getUserDataForMyPage(user);
    }
}
