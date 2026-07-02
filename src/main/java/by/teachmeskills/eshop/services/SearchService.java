package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.dao.IProductRepository;
import by.teachmeskills.eshop.domain.entities.Product;
import by.teachmeskills.eshop.dto.ProductDto;
import by.teachmeskills.eshop.dto.converters.ProductConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchService {

    private final IProductRepository productDao;
    private final ProductConverter productConverter;

    public ResponseEntity<List<ProductDto>> getSearchResult(String searchQuery) throws Exception {
        try {
            List<Product> allProducts = productDao.read();
            List<Product> searchResult = new ArrayList<>(searchProducts(allProducts, searchQuery));
            return new ResponseEntity<>(searchResult.stream()
                    .map(productConverter::toDto).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private Set<Product> searchProducts(List<Product> allProducts, String query) {
        Set<Product> products = new HashSet<>();
        products.addAll(searchByName(allProducts, query));
        products.addAll(searchByDescription(allProducts, query));
        return products;
    }

    private List<Product> searchByName (List<Product> allProducts, String query) {
        List<Product> newProductList = new ArrayList<>();
        for(Product product : allProducts) {
            Matcher matcher = Pattern.compile("\\b" + query + "\\b", Pattern.CASE_INSENSITIVE).matcher(product.getName());
            while (matcher.find()) {
                newProductList.add(product);
            }
        }
        return newProductList;
    }

    private List<Product> searchByDescription (List<Product> allProducts, String query) {
        List<Product> newProductList = new ArrayList<>();
        for(Product product : allProducts) {
            Matcher matcher = Pattern.compile("\\b" + query + "\\b", Pattern.CASE_INSENSITIVE).matcher(product.getDescription());
            while (matcher.find()) {
                newProductList.add(product);
            }
        }
        return newProductList;
    }
}
