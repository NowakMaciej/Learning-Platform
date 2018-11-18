package platform.dto;

import lombok.Data;
import platform.entity.StudentExamId;

@Data
public class StudentExamDto {
	private StudentExamId id;
	private StudentDto studentDto;
	private ExamDto examDto;
	private String result;
}


