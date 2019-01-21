package platform.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.Data;
import platform.entity.Teacher;

@Data
public class ExerciseDto {
	private Long id;
	private String text;
	private String answer;
	private TeacherDto teacherDto;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String difficultyLevel;
	
//	private Set<CategoryDto> categoryDtos;
	private List<CategoryDto> categoryDtos;
	
	private List<ExamDto> examDtos;
}
