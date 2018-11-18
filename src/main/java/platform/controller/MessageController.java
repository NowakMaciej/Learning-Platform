package platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import platform.dto.MessageDto;
import platform.dto.UserDto;
import platform.service.MessageService;

@Controller
@RequestMapping("/message")
@SessionAttributes({"isLoggedIn", "user"})
public class MessageController {
	private MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@ModelAttribute("user")
	private UserDto hasUser() {
		return new UserDto();
	}
	
	@PostMapping("/")
	public String addTweet (@ModelAttribute MessageDto messageDto,
			Model model,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@ModelAttribute("user") UserDto user) {
		if (isLoggedIn) {
			messageService.saveMessage(messageDto, user);
			return "redirect:/user/message/" + messageDto.getReceiver().getId();
		}
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String showUserInformation(@ModelAttribute(name="user", binding=false) UserDto user,
			@ModelAttribute("isLoggedIn") Boolean isLoggedIn,
			@PathVariable Long id,
			Model model) {
		if (isLoggedIn) {
			messageService.markMessageAsRead(id, user);
			model.addAttribute("message", messageService.findMessage(id));
			return "showMessage";
		}
		return "redirect:/";
	}
}
