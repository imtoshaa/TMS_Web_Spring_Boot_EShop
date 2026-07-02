package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.domain.entities.Product;
import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.services.SearchService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

import static by.teachmeskills.eshop.utils.EshopConstants.PRODUCTS_FROM_SEARCH;

@Slf4j
@RestController
@SessionAttributes({PRODUCTS_FROM_SEARCH})
@RequestMapping("/search")
@AllArgsConstructor
@Tag(name = "Search", description = "The search API")
public class SearchController {

    private final SearchService searchService;

    @Operation(
            summary = "Search by query.",
            description = "Search by query."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Search completed",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Search error."
            )
    })
    @PostMapping
    public ResponseEntity<List<ProductDto>> getSearchResult(@RequestParam String searchQuery) throws Exception {
        log.info("Search in progress...");
        return searchService.getSearchResult(searchQuery);
    }

    @ModelAttribute(PRODUCTS_FROM_SEARCH)
    public List<Product> setUpProductsFromSearch() {
        return new ArrayList<>();
    }
}
