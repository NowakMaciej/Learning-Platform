package platform.dto;

import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import platform.utils.DifficultyLevel;
import platform.validator.UniqueEmail;

@Data
//@UniqueEmail
public class UserDto {
	private Long id;
	@NotBlank
	private String firstname;
	@NotBlank
	private String surname;
	@NotBlank
	@Email
	@UniqueEmail
	private String email;
	@NotBlank
	private String password;
	private String role;
	private Boolean active;
	private List<MessageDto> messagesSent;
	private List<MessageDto> messagesReceived;
	private TeacherDto teacher;
	private StudentDto student;
	private DifficultyLevel difficultyLevel;
}
