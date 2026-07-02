package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.services.IProductService;
import by.teachmeskills.eshop.services.impl.ProductServiceImpl;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/product")
@AllArgsConstructor
@Tag(name = "Product", description = "The product API")
public class ProductController {

    private final IProductService productService;

    @Operation(
            summary = "Open product page.",
            description = "Open product page."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product page is opened.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Product error."
            )
    })
    @GetMapping()
    public ResponseEntity<ProductDto> getProductPage(@RequestParam int productId) throws Exception {
        log.info("Redirect to product page id=" + productId);
        return productService.getProductData(productId);
    }
}
