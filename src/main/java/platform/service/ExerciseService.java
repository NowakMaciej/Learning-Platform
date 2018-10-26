package platform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import platform.dto.ExerciseDto;
import platform.dto.TeacherDto;
import platform.entity.Exercise;
import platform.repository.ExerciseRepository;
import platform.repository.TeacherRepository;

@Service
@Transactional
public class ExerciseService {
	private ExerciseRepository exerciseRepository;
	private TeacherRepository teacherRepository;
	private UserServiceImpl userService;
	private CategoryService categoryService;

	public ExerciseService(ExerciseRepository exerciseRepository, TeacherRepository teacherRepository,
			UserServiceImpl userService, CategoryService categoryService) {
		this.exerciseRepository = exerciseRepository;
		this.teacherRepository = teacherRepository;
		this.userService = userService;
		this.categoryService = categoryService;
	}
	
//	public Exercise createExerciseFromDto(ExerciseDto exerciseDto) {
//		Exercise exercise = new Exercise();
//		if (exerciseDto.getId() != null) {
//			exercise.setId(exerciseDto.getId());
//		}
//		exercise.setText(exerciseDto.getText());
//		exercise.setAnswer(exerciseDto.getAnswer());
//		exercise.setTeacher(teacherRepository.getOne(exerciseDto.getTeacherDto().getId()));
//		exercise.setDifficultyLevel(exerciseDto.getDifficultyLevel());
//		exercise.setCreated(LocalDateTime.now());
//		exercise.setUpdated(exercise.getCreated());
//		if (!exerciseDto.getCategoryDtos().isEmpty()) {
//			exercise.setCategories(exerciseDto.getCategoryDtos()
//					.stream()
//					.map(categoryService::createSimpleCategoryFromDto)
//					.collect(Collectors.toList()));
//		}
//		return exercise;
//	}

	public Exercise createSimpleExerciseFromDto(ExerciseDto exerciseDto) {
		Exercise exercise = new Exercise();
		exercise.setId(exerciseDto.getId());
		exercise.setText(exerciseDto.getText());
		exercise.setAnswer(exerciseDto.getAnswer());
		exercise.setDifficultyLevel(exerciseDto.getDifficultyLevel());
		exercise.setCreated(LocalDateTime.now());
		exercise.setUpdated(exercise.getCreated());
		return exercise;
	}
	
	public Exercise createExerciseFromDto(ExerciseDto exerciseDto) {
		Exercise exercise = createSimpleExerciseFromDto(exerciseDto);
		exercise.setTeacher(teacherRepository.getOne(exerciseDto.getTeacherDto().getId()));
		if (!exerciseDto.getCategoryDtos().isEmpty()) {
			exercise.setCategories(exerciseDto.getCategoryDtos()
					.stream()
					.map(categoryService::createSimpleCategoryFromDto)
					.collect(Collectors.toList()));
		}
		return exercise;
	}

	public ExerciseDto getSimpleDtoFromExercise(Exercise exercise) {
		ExerciseDto exerciseDto = new ExerciseDto();
		exerciseDto.setId(exercise.getId());
		exerciseDto.setText(exercise.getText());
		exerciseDto.setAnswer(exercise.getAnswer());
		exerciseDto.setDifficultyLevel(exercise.getDifficultyLevel());
		exerciseDto.setCreated(exercise.getCreated());
		exerciseDto.setUpdated(exercise.getUpdated());
		return exerciseDto;
	}
	
	public ExerciseDto getDtoFromExercise(Exercise exercise) {
		ExerciseDto exerciseDto = getSimpleDtoFromExercise(exercise);
		exerciseDto.setTeacherDto(userService.getDtoFromTeacher(exercise.getTeacher()));
		if (!exercise.getCategories().isEmpty()) {
			exerciseDto.setCategoryDtos(exercise.getCategories()
					.stream()
					.map(categoryService::getSimpleDtoFromCategory)
					.collect(Collectors.toList()));
		}
		return exerciseDto;
	}

	public void updateExercise(ExerciseDto exerciseDto) {
		Exercise exercise = exerciseRepository.getOne(exerciseDto.getId());
		exercise.setText(exerciseDto.getText());
		exercise.setAnswer(exerciseDto.getAnswer());
		exercise.setDifficultyLevel(exerciseDto.getDifficultyLevel());
		exercise.setUpdated(LocalDateTime.now());
		if (!exerciseDto.getCategoryDtos().isEmpty()) {
			exercise.setCategories(exerciseDto.getCategoryDtos()
					.stream()
					.map(categoryService::createCategoryFromDto)
					.collect(Collectors.toList()));
		}
		exerciseRepository.save(exercise);
	}
	
	public void saveExercise(ExerciseDto exerciseDto) {
		exerciseRepository.save(createExerciseFromDto(exerciseDto));
	}

	public ExerciseDto findExerciseById(Long id) {
		return getDtoFromExercise(exerciseRepository.getOne(id));
	}

	public List<ExerciseDto> findAllExercises() {
		return exerciseRepository.findAll()
				.stream()
				.map(this::getDtoFromExercise)
				.collect(Collectors.toList());
	}

	public List<ExerciseDto> findAllExercisesByTeacher(Long id) {
		return exerciseRepository.findAllExercisesByTeacherId(id)
				.stream()
				.map(this::getDtoFromExercise)
				.collect(Collectors.toList());
	}

	public List<ExerciseDto> findLast5ExercisesByTeacher(Long id) {
		return exerciseRepository.findFirst5ExercisesByTeacherIdOrderByCreatedDesc(id)
				.stream()
				.map(this::getDtoFromExercise)
				.collect(Collectors.toList());
	}

	public void deleteExercise(Long id) {
		exerciseRepository.delete(id);
	}
}
