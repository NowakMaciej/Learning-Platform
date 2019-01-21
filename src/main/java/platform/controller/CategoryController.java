package platform.controller;

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
import platform.dto.TeacherDto;
import platform.dto.UserDto;
import platform.service.CategoryService;
import platform.service.UserServiceImpl;

@Controller
@RequestMapping("/category")
@SessionAttributes({"isLoggedIn", "teacher"})
public class CategoryController {
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@ModelAttribute("teacher")
	private TeacherDto getTeacher() {
		return new TeacherDto();
	}
	
	@GetMapping("/")
	public String manageCategories(Model model,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@ModelAttribute(name= "teacher", binding = false) TeacherDto teacher) {
		if(isLoggedIn) {
			model.addAttribute("categoryDto", new CategoryDto());
			model.addAttribute("categories", categoryService.findAllCategoriesByTeacher(teacher.getId()));
			return "teacher/allCategories";
		}
		return "redirect:/";
	}
	
	@PostMapping("/")
	public String showCategories(@ModelAttribute CategoryDto categoryDto, Model model,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@ModelAttribute("teacher") TeacherDto teacher) {
		if (isLoggedIn) {
		categoryService.saveCategory(categoryDto);
		return "redirect:/category/";
		}
		return "redirect:/";
	}

	@GetMapping("/{id}")
	public String editCategory(@ModelAttribute(name = "category", binding = false) CategoryDto category,
		@ModelAttribute("isLoggedIn") Boolean isLoggedIn, @PathVariable Long id, Model model) {
		if (isLoggedIn) {
			model.addAttribute("categoryDto", categoryService.findCategoryById(id));
			return "teacher/editCategory";
		}
		return "redirect:/";
	}
	
	@PostMapping("/{id}")
	public String updateTeacher(@Valid @ModelAttribute(name = "category") CategoryDto category, BindingResult result,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn, Model model) {
		if(result.hasErrors()){
			return "teacher/editCategory";
		}
		categoryService.updateCategory(category);
		return "redirect:/category/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable Long id, Model model, @ModelAttribute("isLoggedIn") Boolean isLoggedIn) {
		if(isLoggedIn) {
			categoryService.deleteCategory(id);
			return "redirect:/category/";
		}
		return "redirect:/";
	}
}
