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
@SessionAttributes({ "isLoggedIn", "user", "teacher", "student", "admin" })
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
		return userService.findAllTeachers();
	}

	@GetMapping("/registerStudent")
	public String registerUser(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "formRegisterStudent";
	}

	@PostMapping("/registerStudent")
	public String showPostRegister(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "formRegisterStudent";
		}
		userService.register(userDto);
		return"registerSuccess";
	}
	
	@GetMapping("/registerTeacher")
	public String registerTeacher(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "formRegisterTeacher";
	}

	@PostMapping("/registerTeacher")
	public String showRegisterTeacher(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "formRegisterTeacher";
		}
		userService.register(userDto);
		return"redirect:/user/admin/";
	}

	@RequestMapping(path = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String logUser(@ModelAttribute("user") UserDto user, Model model, RedirectAttributes redirectAttributes) {
		UserDto currentUser = userService.login(user);
		if (Objects.nonNull(currentUser)) {
			if (currentUser.getActive()) {
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
				if (currentUser.getRole().equals("admin")) {
					model.addAttribute("admin", userService.findUserById(currentUser.getId()));
					return "redirect:/user/admin/";
				}
			}
			String errorMessage2 = "your account is not active";
			redirectAttributes.addFlashAttribute("message", errorMessage2);
			return "redirect:/";
		}
		String errorMessage1 = "login or password are incorrect";
		redirectAttributes.addFlashAttribute("message", errorMessage1);
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
			model.addAttribute("students", userService.findAllStudentsByTeacherId(teacher.getId()));
			model.addAttribute("lastExams", examService.findLast5Exams(teacher.getId()));
			return "pageTeacher";
		}
		return "redirect:/";
	}

	@GetMapping("/student/")
	public String welcomeStudent(@ModelAttribute(name = "student") StudentDto student,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if (isLoggedIn) {
			model.addAttribute("exams", examService.findAllExamsByStudentId(student.getId()));
			return "pageStudent";
		}
		return "redirect:/";
	}
	
	@GetMapping("/admin/")
	public String welcomeAdmin(@ModelAttribute(name = "admin") UserDto admin,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if (isLoggedIn) {
			model.addAttribute("teachers", userService.findAllTeachers());
			model.addAttribute("students", userService.findAllStudents());
			model.addAttribute("unassignedStudents", userService.findAllUnassignedStudents());
			return "pageAdmin";
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
	@GetMapping("/message/{id}")
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
	
	@GetMapping("/edit/{id}")
	public String editUser(@ModelAttribute("user") UserDto user, @ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@PathVariable Long id,
			Model model) {
		if (isLoggedIn) {
			UserDto userDto = userService.findUserById(id);
			if (user.getRole().equals("admin")) {
				if (userDto.getRole().equals("teacher")) {
					model.addAttribute("teacherDto", userService.findTeacherByUserId(userDto.getId()));
					return "formEditTeacher";
				}
				if (userDto.getRole().equals("student")) {
					model.addAttribute("studentDto", userService.findStudentByUserId(userDto.getId()));
					return "formEditStudent";
				}
			}
			model.addAttribute("userDto", userService.findUserById(user.getId()));
			return "formEditUser";
		}
		return "redirect:/";
	}
	
	@PostMapping("/edit/user")
	public String updateUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		userService.updateUser(userDto);
		UserDto editedUser = userService.findUserById(userDto.getId());
		if (editedUser.getRole().equals("teacher")) {
			model.addAttribute("teacher", userService.findTeacherByUserId(editedUser.getId()));
			return "redirect:/user/teacher/";
		}
		if (editedUser.getRole().equals("student")) {
			model.addAttribute("student", userService.findStudentByUserId(editedUser.getId()));
			return "redirect:/user/student/";
		}
		if (editedUser.getRole().equals("admin")) {
			model.addAttribute("admin", userService.findUserById(editedUser.getId()));
			return "redirect:/user/admin/";
		}
		return "redirect:/";
	}

	@PostMapping("/edit/teacher")
	public String updateTeacher(@Valid @ModelAttribute("teacherDto") TeacherDto teacherDto, BindingResult result,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		userService.updateTeacher(teacherDto);
		return "redirect:/user/admin/";
	}
	
	@PostMapping("/edit/student")
	public String updateStudent(@Valid @ModelAttribute("studentDto") StudentDto studentDto, BindingResult result,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		userService.updateStudent(studentDto);
		return "redirect:/user/admin/";
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
	
	@GetMapping("/activation/{id}")
	public String changeActive(@ModelAttribute("isLoggedIn") Boolean isLoggedIn, @PathVariable Long id) {
		if (isLoggedIn) {
			userService.changeActive(id);
			return "redirect:/user/admin/";
		}
		return "redirect:/";
	}

}
