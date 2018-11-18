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

import platform.dto.CategoryDto;
import platform.dto.ExamDto;
import platform.dto.ExerciseDto;
import platform.dto.StudentExamDto;
import platform.dto.TeacherDto;
import platform.dto.UserDto;
import platform.entity.StudentExam;
import platform.service.CategoryService;
import platform.service.ExamService;
import platform.service.ExerciseService;
import platform.service.UserServiceImpl;
import platform.utils.DifficultyLevel;

@Controller
@RequestMapping("/exam")
@SessionAttributes({"isLoggedIn", "teacher"})
public class ExamController {
	private ExamService examService;
	private UserServiceImpl userService;
	private CategoryService categoryService;
	private ExerciseService exerciseService;

	public ExamController(ExamService examService, UserServiceImpl userService, CategoryService categoryService, ExerciseService exerciseService) {
		this.examService = examService;
		this.userService = userService;
		this.categoryService = categoryService;
		this.exerciseService = exerciseService;
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
	public String manageExams(Model model,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@ModelAttribute(name= "teacher", binding = false) TeacherDto teacher) {
		if(isLoggedIn) {
			model.addAttribute("exams", examService.findAllExamsByTeacher(teacher.getId()));
			return "pageExam1";
		}
		return "redirect:/";
	}
	
	@GetMapping("/create")
	public String createExam(Model model,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@ModelAttribute(name= "teacher", binding = false) TeacherDto teacher) {
		if(isLoggedIn) {
			model.addAttribute("examDto", new ExamDto());
			model.addAttribute("categories", categoryService.findAllCategoriesByTeacher(teacher.getId()));
			model.addAttribute("exercises", exerciseService.findAllExercisesByTeacher(teacher.getId()));
			model.addAttribute("students", userService.findAllStudentsByTeacherId(teacher.getId()));
			return "pageExam2";
		}
		return "redirect:/";
	}
	
	@PostMapping("/create")
	public String showExams(@ModelAttribute ExamDto examDto, Model model,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@ModelAttribute(name = "teacher", binding = false) TeacherDto teacher) {
		if (isLoggedIn) {
		examService.saveExam(examDto);
		return "redirect:/exam/";
		}
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String editExam(
		@ModelAttribute("isLoggedIn") Boolean isLoggedIn, @PathVariable Long id, Model model) {
		if (isLoggedIn) {
			ExamDto examDto = examService.findExamById(id);
			model.addAttribute("examDto", examDto);
			model.addAttribute("students", userService.findAllStudentsByTeacherId(examDto.getTeacherDto().getId()));
			model.addAttribute("exercises", exerciseService.findAllExercisesByTeacher(examDto.getTeacherDto().getId()));
			return "formEditExam";
		}
		return "redirect:/";
	}
	
	@PostMapping("/{id}")
	public String updateExam(@Valid @ModelAttribute(name = "exam") ExamDto exam, BindingResult result,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if(result.hasErrors()){
			return "formEditExam";
		}
		examService.updateExam(exam);
		return "redirect:/exam/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteExam(@PathVariable Long id, Model model, @ModelAttribute("isLoggedIn") Boolean isLoggedIn) {
		if(isLoggedIn) {
			examService.deleteExam(id);
		return "redirect:/exam/";
		}
		return "redirect:/";
	}
	
	@GetMapping("/take/{id}")
	public String takeExam(
		@ModelAttribute("isLoggedIn") Boolean isLoggedIn, @PathVariable Long id, Model model) {
		if (isLoggedIn) {
			ExamDto examDto = examService.findExamById(id);
			model.addAttribute("examDto", examDto);
			return "formTakeExam";
		}
		return "redirect:/";
	}
	
	@PostMapping("/take/{id}")
	public String saveExamResult(@Valid @ModelAttribute(name = "exam") ExamDto exam, BindingResult result,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if(result.hasErrors()){
			return "formEditExam";
		}
		model.addAttribute("result", examService.evaluateExam(exam));
		return "examResult";
	}
}



