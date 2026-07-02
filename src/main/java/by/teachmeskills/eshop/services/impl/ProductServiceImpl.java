package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.dao.IProductRepository;
import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.dto.converters.ProductConverter;
import by.teachmeskills.eshop.services.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productDao;
    private final ProductConverter productConverter;

    @Override
    public ResponseEntity<ProductDto> getProductData(int productId) throws Exception {
        ProductDto product = productConverter.toDto(productDao.getProductById(productId));
        log.info("Receiving product data by id=" + productId + " in the process.");
        if (Optional.ofNullable(product).isPresent()) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
