package platform.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import platform.dto.ExerciseDto;
import platform.dto.TeacherDto;
import platform.dto.UserDto;
import platform.service.CategoryService;
import platform.service.ExerciseService;
import platform.utils.DifficultyLevel;

@Controller
@RequestMapping("/exercise")
@SessionAttributes({"isLoggedIn", "teacher"})
public class ExerciseController {
	private ExerciseService exerciseService;
	private CategoryService categoryService;

	public ExerciseController(ExerciseService exerciseService, CategoryService categoryService) {
		this.exerciseService = exerciseService;
		this.categoryService = categoryService;
	}
	
	@ModelAttribute("user")
	private UserDto hasUser() {
		return new UserDto();
	}
	
	@ModelAttribute("teacher")
	private TeacherDto getTeacher() {
		return new TeacherDto();
	}
	
	@ModelAttribute("difficultyLevels")
	private List<DifficultyLevel> getDifficultyLevels() {
		return Arrays.asList(DifficultyLevel.values());
	}
	
	@GetMapping("/")
	public String manageExercises(Model model,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@ModelAttribute(name= "teacher", binding = false) TeacherDto teacher) {
		if(isLoggedIn) {
			model.addAttribute("exerciseDto", new ExerciseDto());
			model.addAttribute("categories", categoryService.findAllCategoriesByTeacher(teacher.getId()));
			model.addAttribute("lastExercises", exerciseService.findLast5ExercisesByTeacher(teacher.getId()));
			return "pageExercises";
		}
		return "redirect:/";
	}
	
	@PostMapping("/")
	public String showExercises (Model model,
			@ModelAttribute(name= "exercise") ExerciseDto exercise,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@ModelAttribute(name= "teacher", binding = false) TeacherDto teacher) {
		if (isLoggedIn) {
			exerciseService.saveExercise(exercise);
			return "redirect:/exercise/";
		}
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String editExercise(@ModelAttribute(name = "exercise", binding = false) ExerciseDto exercise,
		@ModelAttribute("isLoggedIn") Boolean isLoggedIn, @PathVariable Long id, Model model) {
		if (isLoggedIn) {
			ExerciseDto exerciseDto = exerciseService.findExerciseById(id);
			model.addAttribute("exerciseDto", exerciseDto);
			model.addAttribute("categories", categoryService.findAllCategoriesByTeacher(exerciseDto.getTeacherDto().getId()));
			return "formEditExercise";
		}
		return "redirect:/";
	}
	
	@PostMapping("/{id}")
	public String updateExercise(@Valid @ModelAttribute(name = "exercise") ExerciseDto exercise, BindingResult result,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if(result.hasErrors()){
			return "formEditExercise";
		}
		exerciseService.updateExercise(exercise);
		return "redirect:/exercise/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteExercise(@PathVariable Long id, Model model, @ModelAttribute("isLoggedIn") Boolean isLoggedIn) {
		if(isLoggedIn) {
			exerciseService.deleteExercise(id);
		return "redirect:/exercise/";
		}
		return "redirect:/";
	}
}
