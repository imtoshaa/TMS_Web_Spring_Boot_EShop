package by.teachmeskills.eshop.services.impl;

import by.teachmeskills.eshop.dao.ICategoryRepository;
import by.teachmeskills.eshop.dao.IProductRepository;
import by.teachmeskills.eshop.dto.CategoryDto;
import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.dto.converters.CategoryConverter;
import by.teachmeskills.eshop.dto.converters.ProductConverter;
import by.teachmeskills.eshop.services.ICategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryDao;
    private final IProductRepository productDao;
    private final ProductConverter productConverter;
    private final CategoryConverter categoryConverter;


    @Override
    public ResponseEntity<List<CategoryDto>> getCategories() throws Exception {
        try {
            List<CategoryDto> dtoList = categoryDao.read().stream()
                    .map(categoryConverter::toDto).toList();
            log.info("Getting all categories.");
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ProductDto>> getCategoryData(int categoryId) throws Exception {
        try {
            List<ProductDto> dtoList = productDao.getProductsByCategoryId(categoryId).stream()
                    .map(productConverter::toDto).toList();
            log.info("Getting a category by id=" + categoryId);
            return new ResponseEntity<>(dtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}