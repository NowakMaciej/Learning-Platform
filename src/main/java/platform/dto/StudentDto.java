package platform.dto;

import java.util.List;

import lombok.Data;
import platform.utils.DifficultyLevel;

@Data
public class StudentDto {
	private Long id;
	private UserDto user;
	private DifficultyLevel difficultyLevel;
	private TeacherDto teacher;
	private List<ExamDto> examDtos;
	
	public String getName() {
		return this.user.getFirstname() + " " + this.user.getSurname();
	}
}
