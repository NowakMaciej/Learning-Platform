package platform.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import platform.utils.DifficultyLevel;

@Data
public class ExamDto {
	private Long id;
	private String title;
	private TeacherDto teacherDto;
	
//	private List<StudentDto> studentDtos;
	private List<StudentExamDto> studentExamDtos;
	
	private DifficultyLevel difficultyLevel;
	private LocalDateTime created;
	private LocalDateTime updated;
	private Boolean active;
	private List<ExerciseDto> exerciseDtos;
}
