package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.dao.IProductRepository;
import by.teachmeskills.eshop.domain.Cart;
import by.teachmeskills.eshop.domain.entities.Order;
import by.teachmeskills.eshop.domain.entities.Product;
import by.teachmeskills.eshop.domain.entities.User;
import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.dto.converters.ProductConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class CartService {

    private final IProductRepository productDao;
    private final IUserService userService;
    private final IOrderService orderService;
    private final ProductConverter productConverter;

    public ResponseEntity<List<ProductDto>> openCart(Cart cart) throws Exception {
        try {
        List<ProductDto> dtoList = cart.getProducts().values().stream()
                .map(productConverter::toDto).toList();
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<ProductDto>> addProductToCart(Cart cart, int productId) throws Exception {
        Product product = productDao.getProductById(productId);
        if (Optional.ofNullable(product).isPresent()) {
            log.info("Product was added to cart");
            return new ResponseEntity<>(cart.getProducts().values().stream()
                    .map(productConverter::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<ProductDto>> removeProduct(Cart cart, int removeId) {
        if (cart.getProducts().get(removeId) == null) {
            return new ResponseEntity<>(cart.getProducts().values().stream()
                    .map(productConverter::toDto)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        } else {
            cart.removeProduct(removeId);
            log.info("Product was deleted from cart.");
            return new ResponseEntity<>(cart.getProducts().values().stream()
                    .map(productConverter::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    public ResponseEntity<List<ProductDto>> clearCart(Cart cart) {
        cart.clear();
        log.info("Shopping cart has been cleared!");
        return new ResponseEntity<>(cart.getProducts().values().stream()
                .map(productConverter::toDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    public ResponseEntity<List<ProductDto>> confirmOrder(Cart cart, User user) throws Exception {
        if (user != null && userService.isAuthenticated(user)) {
            Date orderDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(orderDate);
            Order order = Order.builder()
                    .date(currentTime)
                    .price(cart.getTotalPrice())
                    .user(user)
                    .build();
            for(Product product : cart.getProducts().values()) {
                orderService.create(order, product);
            }
            cart.clear();
            log.info("Order is made!");
            return new ResponseEntity<>(cart.getProducts().values().stream()
                    .map(productConverter::toDto)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(cart.getProducts().values().stream()
                    .map(productConverter::toDto)
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }
    }
}
