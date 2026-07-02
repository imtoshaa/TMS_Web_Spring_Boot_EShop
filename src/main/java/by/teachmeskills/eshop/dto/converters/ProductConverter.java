package by.teachmeskills.eshop.dto.converters;

import by.teachmeskills.eshop.domain.entities.Product;
import by.teachmeskills.eshop.dto.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductConverter {


    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .img(product.getImg())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public Product toEntity(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .img(productDto.getImg())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .name(productDto.getName())
                .build();
    }
}
