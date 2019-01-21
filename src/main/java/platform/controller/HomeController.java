package platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import platform.dto.StudentDto;
import platform.dto.TeacherDto;
import platform.dto.UserDto;


@Controller
@RequestMapping("/")
@SessionAttributes({ "isLoggedIn", "user", "teacher", "student" })
public class HomeController {
	
	@ModelAttribute("isLoggedIn")
	private Boolean isLoggedIn() {
		return false;
	}
	
	@ModelAttribute("user")
	private UserDto hasUser() {
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
	
	@GetMapping
	public String getWelcomePage(@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if (isLoggedIn) {
			return "redirect:/user";
		}
		model.addAttribute("user", new UserDto());
		return "general/welcomePage";
	}
	

}
