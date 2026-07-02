package by.teachmeskills.eshop.dto.converters;

import by.teachmeskills.eshop.domain.entities.Category;
import by.teachmeskills.eshop.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryConverter {

    public CategoryDto toDto (Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .description(category.getDescription())
                .img(category.getImg())
                .name(category.getName())
                .build();
    }

    public Category toEntity (CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .img(categoryDto.getImg())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
    }
}
