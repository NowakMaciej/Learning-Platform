package platform.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import platform.entity.User;

@Data
public class CategoryDto {
	private Long id;
	private String name;
	private LocalDateTime created;
	private LocalDateTime updated;
	private TeacherDto teacherDto;
	private List<ExerciseDto> exerciseDtos;
}
