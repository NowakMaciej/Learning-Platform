package platform.dto;

import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import platform.utils.DifficultyLevel;
import platform.validator.UniqueEmail;

@Data
public class UserDto {
	private Long id;
	@NotEmpty
//	@NotBlank
	private String firstname;
	@NotEmpty
	@NotBlank
	private String surname;
	@NotEmpty
	@NotBlank
	@Email
	@UniqueEmail
	private String email;
	@NotEmpty
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
