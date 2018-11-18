//package platform.service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//import javax.transaction.Transactional;
//
//import org.springframework.stereotype.Service;
//
//import platform.dto.ExamDto;
//import platform.dto.ExerciseDto;
//import platform.dto.TeacherDto;
//import platform.entity.Exam;
//import platform.entity.Exercise;
//import platform.repository.ExamRepository;
//import platform.repository.TeacherRepository;
//
//@Service
//@Transactional
//public class StudentExamService {
//	private StudentExamRepository studentExamRepository;
//
//	public ExamService(StudentExamRepository studentExamRepository) {
//		this.studentExamRepository = studentExamRepository;
//	}
//
//	public Exam createStudentExamFromDto(ExamDto examDto) {
//		StudentExam studentExam = new StudentExam();
//		if (!examDto.getStudentDtos().isEmpty()) {
//			exam.setStudents(examDto.getStudentDtos().stream()
//					.map(userService::createSimpleStudentFromDto).collect(Collectors.toList()));
//		}		
//		exam.setDifficultyLevel(examDto.getDifficultyLevel());
//		exam.setCreated(LocalDateTime.now());
//		exam.setUpdated(exam.getCreated());
//		exam.setActive(true);
//		if (!examDto.getExerciseDtos().isEmpty()) {
//			exam.setExercises(examDto.getExerciseDtos().stream()
//					.map(exerciseService::createSimpleExerciseFromDto).collect(Collectors.toList()));
//		}		
//		return exam;
//	}
//	
//	public ExamDto getSimpleDtoFromExam(Exam exam) {
//		ExamDto examDto = new ExamDto();
//		examDto.setId(exam.getId());
//		examDto.setTitle(exam.getTitle());
//		examDto.setDifficultyLevel(exam.getDifficultyLevel());
//		examDto.setCreated(exam.getCreated());
//		examDto.setUpdated(exam.getUpdated());
//		return examDto;
//	}
//
//	public ExamDto getDtoFromExam(Exam exam) {
//		ExamDto examDto = getSimpleDtoFromExam(exam);
//		examDto.setTeacherDto(userService.getSimpleDtoFromTeacher(exam.getTeacher()));
//		if (!exam.getStudents().isEmpty()) {
//			examDto.setStudentDtos(exam.getStudents()
//					.stream()
//					.map(userService::getSimpleDtoFromStudent)
//					.collect(Collectors.toList()));
//		}
//		if (!exam.getExercises().isEmpty()) {
//			examDto.setExerciseDtos(exam.getExercises()
//					.stream()
//					.map(exerciseService::getDtoFromExercise)
//					.collect(Collectors.toList()));
//		}
//		return examDto;
//	}
//}
