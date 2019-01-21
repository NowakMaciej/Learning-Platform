package platform.dto;

import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class TeacherDto {
	private Long id;
	private UserDto user;
	private List<StudentDto> students;
	
//	private Set<CategoryDto> categories;
	private List<CategoryDto> categories;

	private List<ExamDto> examDtos;
	
	public String getName() {
		return this.user.getFirstname() + " " + this.user.getSurname();
	}
}
