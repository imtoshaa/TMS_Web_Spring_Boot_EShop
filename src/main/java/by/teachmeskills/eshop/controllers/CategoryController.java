package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.domain.Cart;
import by.teachmeskills.eshop.domain.entities.User;
import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.services.ICategoryService;
import by.teachmeskills.eshop.services.impl.CategoryServiceImpl;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

import static by.teachmeskills.eshop.utils.EshopConstants.CATEGORY;
import static by.teachmeskills.eshop.utils.EshopConstants.SHOPPING_CART;
import static by.teachmeskills.eshop.utils.EshopConstants.USER;

@Slf4j
@RestController
@SessionAttributes({CATEGORY, USER, SHOPPING_CART})
@RequestMapping("/")
@AllArgsConstructor
@Tag(name = "Categories", description = "The categories API")
public class CategoryController {

    private final ICategoryService categoryService;

    @Operation(
            summary = "Getting categories.",
            description = "Getting categories."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categories are taking.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Categories error."
            )
    })
    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getCategoriesPage() throws Exception {
        log.info("Redirect to all categories page.");
        return categoryService.getCategories();
    }

    @Operation(
            summary = "Getting data from category",
            description = "Getting data from category"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Data is taking.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Categories error."
            )
    })
    @GetMapping("/category/")
    public ResponseEntity<List<ProductDto>> getCategoryPage(@RequestParam int categoryId) throws Exception {
        log.info("Redirect to category page id=" + categoryId);
        return categoryService.getCategoryData(categoryId);
    }
}
