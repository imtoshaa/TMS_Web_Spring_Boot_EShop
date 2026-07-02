package by.teachmeskills.eshop.controllers;

import by.teachmeskills.eshop.domain.Cart;
import by.teachmeskills.eshop.domain.entities.Product;
import by.teachmeskills.eshop.domain.entities.User;
import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.dto.UserDto;
import by.teachmeskills.eshop.services.CartService;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

import static by.teachmeskills.eshop.utils.EshopConstants.SHOPPING_CART;
import static by.teachmeskills.eshop.utils.EshopConstants.USER;

@Slf4j
@RestController
@SessionAttributes({SHOPPING_CART, USER})
@RequestMapping("/cart")
@AllArgsConstructor
@Tag(name = "Cart", description = "The cart API")
public class CartController {

    private final CartService cartService;

    @Operation(
            summary = "Open cart.",
            description = "Open cart."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cart is open;",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Cart error."
            )
    })
    @GetMapping("/open")
    public ResponseEntity<List<ProductDto>> redirectToShoppingCart(HttpSession session) throws Exception {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        log.info("Redirect to cart page.");
        return cartService.openCart(cart);
    }

    @Operation(
            summary = "Adding product to cart.",
            description = "Adding product to cart."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product is added.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Cart error."
            )
    })
    @GetMapping("/add/")
    public ResponseEntity<List<ProductDto>> addProductToCart(HttpSession session,
                                         @RequestParam int productId) throws Exception {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        log.info("Product with id=" + productId + " added to cart.");
        return cartService.addProductToCart(cart, productId);
    }

    @Operation(
            summary = "Deleting product from cart.",
            description = "Deleting product from cart."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product is deleted.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Cart error."
            )
    })
    @GetMapping("/delete/")
    public ResponseEntity<List<ProductDto>> delProductFromCart(HttpSession session,
                                           @RequestParam int removeId){
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        log.info("Product with id=" + removeId + " has been removed from the shopping cart.");
        return cartService.removeProduct(cart, removeId);
    }

    @Operation(
            summary = "Clearing cart.",
            description = "Clearing cart."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product is deleted.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Cart error."
            )
    })
    @GetMapping("/clear")
    public ResponseEntity<List<ProductDto>> clearUserCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        log.info("The cart is cleared.");
        return cartService.clearCart(cart);
    }

    @Operation(
            summary = "Confirming order.",
            description = "Confirming order"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order is confirmed.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Cart error."
            )
    })
    @GetMapping("/confirmOrder")
    public ResponseEntity<List<ProductDto>> confirmOrder(HttpSession session) throws Exception {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        User user = (User) session.getAttribute("user");
        return cartService.confirmOrder(cart, user);
    }
}
