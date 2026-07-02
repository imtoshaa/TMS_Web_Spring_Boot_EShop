package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.domain.entities.Category;
import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ICategoryService {

    ResponseEntity<List<CategoryDto>> getCategories() throws Exception;

    ResponseEntity<List<ProductDto>> getCategoryData(int categoryId) throws Exception;
}
