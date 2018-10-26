package platform.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import platform.dto.CategoryDto;
import platform.service.CategoryService;

public class CategoryConverter implements Converter<String, CategoryDto>{
	@Autowired
	CategoryService categoryService;

	@Override
	public CategoryDto convert(String source) {
		return this.categoryService.findCategoryById(Long.parseLong(source));
	}

}
