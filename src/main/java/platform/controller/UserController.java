package platform.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import platform.dto.MessageDto;
import platform.dto.StudentDto;
import platform.dto.TeacherDto;
import platform.dto.UserDto;
import platform.repository.UserRepository;
import platform.service.ExamService;
import platform.service.MessageService;
import platform.service.UserServiceImpl;
import platform.utils.DifficultyLevel;
import platform.utils.UserRole;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "isLoggedIn", "user", "teacher", "student" })
public class UserController {

	private UserServiceImpl userService;
	private MessageService messageService;
	private ExamService examService;

	public UserController(UserServiceImpl userService, MessageService messageService, ExamService examService) {
		this.userService = userService;
		this.messageService = messageService;
		this.examService = examService;
	}

	@ModelAttribute("user")
	private UserDto getUser() {
		return new UserDto();
	}

	@ModelAttribute("teacher")
	private TeacherDto getTeacher() {
		return new TeacherDto();
	}

	@ModelAttribute("student")
	private StudentDto getStudent() {
		return new StudentDto();
	}

	@ModelAttribute("roles")
	private List<UserRole> getRoles() {
		return Arrays.asList(UserRole.values());
	}

	@ModelAttribute("difficultyLevels")
	private List<DifficultyLevel> getDifficultyLevels() {
		return Arrays.asList(DifficultyLevel.values());
	}

	@ModelAttribute("teachers")
	private List<TeacherDto> getTeachers() {
		return userService.findTeachers();
	}

	@GetMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "formRegister";
	}

	@PostMapping("/register")
	public String showPostRegister(@Valid @ModelAttribute("userDto") UserDto user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "formRegister";
		}
		userService.register(user);
		model.addAttribute("user", user);
		return "redirect:/user/login";
	}

	@RequestMapping(path = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String logUser(@ModelAttribute("user") UserDto user, Model model, RedirectAttributes redirectAttributes) {
		if (Objects.nonNull(userService.login(user))) {
			UserDto currentUser = userService.login(user);
			model.addAttribute("isLoggedIn", true);
			model.addAttribute("user", currentUser);
			if (currentUser.getRole().equals("teacher")) {
				model.addAttribute("teacher", userService.findTeacherByUserId(currentUser.getId()));
				return "redirect:/user/teacher/";
			}
			if (currentUser.getRole().equals("student")) {
				model.addAttribute("student", userService.findStudentByUserId(currentUser.getId()));
				return "redirect:/user/student/";
			}
		}
		String errorMessage = "login or password are incorrect";
		redirectAttributes.addFlashAttribute("message", errorMessage);
		return "redirect:/";
	}

	@GetMapping(path = "/logout")
	public String logoutUser(Model model, @ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@ModelAttribute("user") UserDto user) {
		model.addAttribute("isLoggedIn", false);
		model.addAttribute("user", new UserDto());
		model.addAttribute("teacher", new TeacherDto());
		model.addAttribute("student", new StudentDto());
		return "redirect:/";
	}

	@GetMapping("/teacher/")
	public String welcomeTeacher(@ModelAttribute(name = "teacher") TeacherDto teacher,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if (isLoggedIn) {
			model.addAttribute("students", userService.findAllStudentsByTeacherId(teacher.getUser().getId()));
//			model.addAttribute("students", userService.findAllStudentsByTeacherId(teacher.getId()));
			model.addAttribute("lastExams", examService.findLast5Exams(teacher.getId())); //???
			return "pageTeacher";
		}
		return "redirect:/";
	}

	@GetMapping("/student/")
	public String welcomeStudent(@ModelAttribute(name = "student") StudentDto student,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if (isLoggedIn) {
			model.addAttribute("exams", examService.findAllExamsByStudentId(student.getId())); // getUser().getId()
			return "pageStudent";
		}
		return "redirect:/";
	}

	/*
	 * jesli będzie samo id to Spring podmieni usera na usera o danym "id" - jest to
	 * magia ModelAttribute. Dlatego albo trzeba zmienic nazwe na niebędącą nazwą
	 * pola klasy (id) np. "IdNumber" lub dodać binding=false. Wiecej w linku.
	 * Uwaga! Komentarz na samym dole zawiera drugiego linka i tam jest podpowiedź.
	 * Tutaj jest fajne objasnienie
	 * https://stackoverflow.com/questions/17722641/spring-sessionattribute-how-to-
	 * retrieve-the-session-object-in-same-controller
	 */
	@GetMapping("/{id}")
	public String showUserInformation(@ModelAttribute(name = "user", binding = false) UserDto user,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, @PathVariable Long id, Model model) {
		if (isLoggedIn) {
			model.addAttribute("messageDto", new MessageDto());
			model.addAttribute("userOfId", userService.findUserById(id));
			if (userService.findUserByEmail(user.getEmail()).getRole().equals("teacher")) {
				return "showTeacher";
			} else {
				return "showStudent";
			}
		}
		return "redirect:/";
	}
	
	@GetMapping("/edit")
	public String editUser(@ModelAttribute("user") UserDto user, @ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			Model model) {
		if (isLoggedIn) {
			if (user.getRole().equals("teacher")) {
				model.addAttribute("teacherDto", userService.findTeacherByUserId(user.getId()));
				return "formEditTeacher";
			}
			if (user.getRole().equals("student")) {
				model.addAttribute("studentDto", userService.findStudentByUserId(user.getId()));
				return "formEditStudent";
			}
		}
		return "redirect:/";
	}

	@PostMapping("/edit/teacher")
	public String updateTeacher(@Valid @ModelAttribute("teacherDto") TeacherDto teacherDto, BindingResult result,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		userService.updateTeacher(teacherDto);
		model.addAttribute("teacher", userService.findTeacherByUserId(teacherDto.getUser().getId()));
//		model.addAttribute("teacher", teacherDto);
		return "redirect:/user/teacher/";
	}
	
	@PostMapping("/edit/student")
	public String updateStudent(@Valid @ModelAttribute("studentDto") StudentDto studentDto, BindingResult result,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		userService.updateStudent(studentDto);
		model.addAttribute("student", userService.findStudentByUserId(studentDto.getUser().getId()));
//		model.addAttribute("student", "studentDto");
		return "redirect:/user/student/";
	}

	@GetMapping("/messages")
	public String showMessages(Model model, @ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@ModelAttribute("user") UserDto user) {
		if (isLoggedIn) {
			model.addAttribute("sentMessages", messageService.findAllMessagesBySenderId(user.getId()));
			model.addAttribute("receivedMessages", messageService.findAllMessagesByReceiverId(user.getId()));
			return "showMessages";
		}
		return "redirect:/";
	}

}
