package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.domain.entities.Product;
import by.teachmeskills.eshop.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface IProductService {

    ResponseEntity <ProductDto> getProductData(int productId) throws Exception;
}
