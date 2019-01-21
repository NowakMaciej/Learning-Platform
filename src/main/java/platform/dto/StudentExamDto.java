package platform.dto;

import lombok.Data;
import platform.entity.StudentExamId;

@Data
public class StudentExamDto {
//	private StudentExamId id;
	private Long id;
	private Boolean active;
	
	private StudentDto studentDto;
	private ExamDto examDto;
	private Integer result;
	
	public StudentExamDto () {}
	
	public StudentExamDto (StudentDto studentDto, ExamDto examDto) {
		this.studentDto = studentDto;
		this.examDto = examDto;
	}
}


