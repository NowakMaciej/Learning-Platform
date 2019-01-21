package platform.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import platform.dto.CategoryDto;
import platform.dto.ExamDto;
import platform.dto.ExerciseDto;
import platform.dto.StudentDto;
import platform.dto.StudentExamDto;
import platform.dto.TeacherDto;
import platform.dto.UserDto;
import platform.entity.StudentExam;
import platform.service.CategoryService;
import platform.service.ExamService;
import platform.service.ExerciseService;
import platform.service.StudentExamService;
import platform.service.UserServiceImpl;
import platform.utils.DifficultyLevel;

@Controller
@RequestMapping("/exam")
@SessionAttributes({"isLoggedIn", "teacher", "student"})
public class ExamController {
	private ExamService examService;
	private UserServiceImpl userService;
	private CategoryService categoryService;
	private ExerciseService exerciseService;
	private StudentExamService studentExamService;

	public ExamController(ExamService examService, UserServiceImpl userService,
	CategoryService categoryService, ExerciseService exerciseService, StudentExamService studentExamService) {
		this.examService = examService;
		this.userService = userService;
		this.categoryService = categoryService;
		this.exerciseService = exerciseService;
		this.studentExamService = studentExamService;
	}
	
	@ModelAttribute("teacher")
	private TeacherDto getTeacher() {
		return new TeacherDto();
	}
	
	@ModelAttribute("student")
	private StudentDto getStudent() {
		return new StudentDto();
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
			return "teacher/allExams";
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
			model.addAttribute("students", userService.findAllActiveStudentsByTeacherId(teacher.getId()));
			return "teacher/newExam";
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
			model.addAttribute("students", userService.findAllActiveStudentsByTeacherId(examDto.getTeacherDto().getId()));
			model.addAttribute("exercises", exerciseService.findAllExercisesByTeacher(examDto.getTeacherDto().getId()));
			return "teacher/editExam";
		}
		return "redirect:/";
	}
	
	@PostMapping("/{id}")
	public String updateExam(@Valid @ModelAttribute(name = "exam") ExamDto exam, BindingResult result,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if(result.hasErrors()){
			return "teacher/editExam";
		}
		examService.updateExam(exam);
		return "redirect:/exam/";
	}
	
	@GetMapping("/assign/{id}")
	public String assignExam(@PathVariable Long id, Model model,
		@ModelAttribute("isLoggedIn") Boolean isLoggedIn, 
		@ModelAttribute(name= "teacher", binding = false) TeacherDto teacher) {		
		if (isLoggedIn) {
			model.addAttribute("examDto", examService.findExamById(id));
			model.addAttribute("students", userService.findAllActiveStudentsByTeacherId(teacher.getId()));
			return "teacher/assignExam";
		}
		return "redirect:/";
	}
	
	@PostMapping("/assign/{id}")
	public String postAssignExam (@RequestParam (required = false) List<StudentDto> listOfStudents,
			@PathVariable Long id,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if (Objects.isNull(listOfStudents)){
			listOfStudents = new ArrayList<StudentDto>();
		}
		studentExamService.saveOrUpdate(listOfStudents, id);
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
	
	@GetMapping("/take/{examId}")
	public String takeExam( @ModelAttribute(name = "student", binding = false) StudentDto student,
		@ModelAttribute("isLoggedIn") Boolean isLoggedIn, @PathVariable Long examId, Model model) {
		if (isLoggedIn) {
//			ExamDto examDto = examService.findExamById(examId);
//			model.addAttribute("examDto", examDto);
//			model.addAttribute("examDto", studentExamService.findStudentExamByStudentIdAndExamId(student.getId(), examId).getExamDto());
			model.addAttribute("studentExamDto", studentExamService.findStudentExamByStudentIdAndExamId(student.getId(), examId));
			return "student/takeExam";
		}
		return "redirect:/";
	}
	
	@PostMapping("/take/{id}")
	public String saveExamResult(@Valid @ModelAttribute(name = "studentExamDto") StudentExamDto studentExamDto, BindingResult result,
//	public String saveExamResult(@Valid @ModelAttribute(name = "examDto") ExamDto examDto, BindingResult result,
		@ModelAttribute(name = "student", binding = false) StudentDto student, @PathVariable Long id,
		@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if(result.hasErrors()){
			return "student/takeExam";
		}
		model.addAttribute("result", examService.evaluateExam(student.getId(), studentExamDto.getExamDto()));
//		model.addAttribute("result", examService.evaluateExam(student.getId(), examDto));
		return "student/showExamResult";
	}
	
	@GetMapping("/activate/{id}")
	public String toggleExamActiveState(@ModelAttribute("isLoggedIn") Boolean isLoggedIn, @PathVariable Long id) {
		if (isLoggedIn) {
			studentExamService.toggleStudentExamActiveState(id);
			return "redirect:/exam/";
		}
		return "redirect:/";
	}
	
	@GetMapping("/view/{id}")
	public String showStudentExamDetails(@ModelAttribute(name = "isLoggedIn") Boolean isLoggedIn, @PathVariable Long id, Model model){
		if(isLoggedIn){
			model.addAttribute("studentExam", studentExamService.findStudentExamById(id));
			return "student/viewStudentExam";
		}
		return "redirect:/";
	}
}



