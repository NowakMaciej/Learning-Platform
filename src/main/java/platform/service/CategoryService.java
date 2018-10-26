package platform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import platform.dto.CategoryDto;
import platform.dto.TeacherDto;
import platform.entity.Category;
import platform.repository.CategoryRepository;
import platform.repository.TeacherRepository;

@Service
@Transactional
public class CategoryService {
	private CategoryRepository categoryRepository;
	private UserServiceImpl userService;
	private TeacherRepository teacherRepository;
	private ExerciseService exerciseService;

	public CategoryService(CategoryRepository categoryRepository, UserServiceImpl userService,
			TeacherRepository teacherRepository, @Lazy ExerciseService exerciseService) {
		this.categoryRepository = categoryRepository;
		this.userService = userService;
		this.teacherRepository = teacherRepository;
		this.exerciseService = exerciseService;
	}

	public Category createSimpleCategoryFromDto(CategoryDto categoryDto) {
		Category category = new Category();
		if (categoryDto.getId() != null) {
			category.setId(categoryDto.getId());
		}
		category.setName(categoryDto.getName());
		category.setCreated(LocalDateTime.now());
		category.setUpdated(category.getCreated());
		return category;
	}
	
	public Category createCategoryFromDto(CategoryDto categoryDto) {
		Category category = createSimpleCategoryFromDto(categoryDto);
		category.setTeacher(teacherRepository.getOne(categoryDto.getTeacherDto().getId()));
//		SKORO TO ENCJA EXERCISE JEST WLASCICIELEM RELACJI I ODPOWIADA ZA UPDATE TABELI ZLACZENIOWEJ(?) TO NIE MA POTRZEBY ZAPISYWAC TEGO W ENCJI CATEGORY
//		if(Objects.nonNull(categoryDto.getExerciseDtos()) && !categoryDto.getExerciseDtos().isEmpty()) {
//			category.setExercises(categoryDto.getExerciseDtos()
//				.stream()
//				.map(exerciseService::createExerciseFromDto)
//				.collect(Collectors.toList()));
//		}
		return category;
	}
	
	public CategoryDto getSimpleDtoFromCategory(Category category) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());
		categoryDto.setCreated(category.getCreated());
		categoryDto.setUpdated(category.getUpdated());
		return categoryDto;
	}
	
	public CategoryDto getDtoFromCategory(Category category) {
		CategoryDto categoryDto = getSimpleDtoFromCategory(category);
		categoryDto.setTeacherDto(userService.getDtoFromTeacher(category.getTeacher()));
		if (Objects.nonNull(category.getExercises()) && !category.getExercises().isEmpty()) {
			categoryDto.setExerciseDtos(category.getExercises()
					.stream()
					.map(exerciseService::getSimpleDtoFromExercise)
					.collect(Collectors.toList()));
		}
		return categoryDto;
	}

	public void updateCategory(CategoryDto categoryDto) {
		Category category = categoryRepository.getOne(categoryDto.getId());
		category.setName(categoryDto.getName());
		category.setUpdated(LocalDateTime.now());
		categoryRepository.save(category);
	}

	public void saveCategory(CategoryDto categoryDto) {
		categoryRepository.save(createCategoryFromDto(categoryDto));
	}

	public CategoryDto findCategoryById(Long id) {
		return getDtoFromCategory(categoryRepository.getOne(id));
	}

	public List<CategoryDto> findAllCategories() {
		return categoryRepository.findAll()
				.stream()
				.map(this::getDtoFromCategory)
				.collect(Collectors.toList());
	}

	public List<CategoryDto> findAllCategoriesByTeacher(Long id) {
		return categoryRepository.findAllCategoriesByTeacherId(id)
				.stream()
				.map(this::getDtoFromCategory)
				.collect(Collectors.toList());
	}

	public void deleteCategory(Long id) {
		categoryRepository.delete(id);
	}
}
