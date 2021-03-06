package platform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import platform.dto.ExamDto;
import platform.dto.ExerciseDto;
import platform.dto.StudentExamDto;
import platform.dto.TeacherDto;
import platform.entity.Exam;
import platform.entity.Exercise;
import platform.entity.StudentExam;
import platform.repository.ExamRepository;
import platform.repository.TeacherRepository;

@Service
@Transactional
public class ExamService {
	private ExamRepository examRepository;
	private ExerciseService exerciseService;
	private TeacherRepository teacherRepository;
	private UserServiceImpl userService;
	private StudentExamService studentExamService;

	public ExamService(ExamRepository examRepository, ExerciseService exerciseService, TeacherRepository teacherRepository,
			UserServiceImpl userService, @Lazy StudentExamService studentExamService) {
		this.examRepository = examRepository;	
		this.exerciseService = exerciseService;
		this.teacherRepository = teacherRepository;
		this.userService = userService;
		this.studentExamService = studentExamService;
	}
	
	public Exam createSimpleExamFromDto(ExamDto examDto) {
		Exam exam = new Exam();
		exam.setId(examDto.getId());
		exam.setTitle(examDto.getTitle());
		exam.setDifficultyLevel(examDto.getDifficultyLevel());
		exam.setCreated(LocalDateTime.now());
		exam.setUpdated(exam.getCreated());
		exam.setActive(false);
		return exam;
	}
	
	public Exam createExamFromDto(ExamDto examDto) {
		Exam exam = createSimpleExamFromDto(examDto);
		exam.setTeacher(teacherRepository.getOne(examDto.getTeacherDto().getId())); // dziala dopoki teacher i simpleTeacher to to samo
		if (!examDto.getExerciseDtos().isEmpty()) {
			exam.setExercises(examDto.getExerciseDtos().stream()
					.map(exerciseService::createSimpleExerciseFromDto).collect(Collectors.toList()));
		}
		return exam;
	}
	
//	public Exam createExamFromDto(ExamDto examDto) {
//		Exam exam = new Exam();
//		if (examDto.getId() != null) {
//			exam.setId(examDto.getId());
//		}
//		exam.setTitle(examDto.getTitle());
//		exam.setTeacher(teacherRepository.getOne(examDto.getTeacherDto().getId()));
////	if (!examDto.getStudentDtos().isEmpty()) {
////		exam.setStudents(examDto.getStudentDtos().stream()
////				.map(userService::createSimpleStudentFromDto)
////				.collect(Collectors.toList()));
////	}		
////	if (!examDto.getStudentExamDtos().isEmpty()) {
////		exam.setStudents(examDto.getStudentExamDtos().stream()
////				.map(this::createStudentExamFromDto)
////				.collect(Collectors.toList()));
////	}
//		exam.setDifficultyLevel(examDto.getDifficultyLevel());
//		exam.setCreated(LocalDateTime.now());
//		exam.setUpdated(exam.getCreated());
//		exam.setActive(true);
//		if (!examDto.getExerciseDtos().isEmpty()) {
//			exam.setExercises(examDto.getExerciseDtos().stream()
//					.map(exerciseService::createSimpleExerciseFromDto)
//					.collect(Collectors.toList()));
//		}		
//		return exam;
//	}
	
	public ExamDto getSimpleDtoFromExam(Exam exam) {
		ExamDto examDto = new ExamDto();
		examDto.setId(exam.getId());
		examDto.setTitle(exam.getTitle());
		examDto.setDifficultyLevel(exam.getDifficultyLevel());
		examDto.setCreated(exam.getCreated());
		examDto.setUpdated(exam.getUpdated());
		examDto.setActive(exam.getActive());
		if(Objects.nonNull(exam.getTeacher())) {
			examDto.setTeacherDto(userService.getSimpleDtoFromTeacher(exam.getTeacher()));
		}
		
		if (!exam.getExercises().isEmpty()) {
			examDto.setExerciseDtos(exam.getExercises()
					.stream()
					.map(exerciseService::getDtoFromExercise)
					.collect(Collectors.toList()));
		}
		return examDto;
	}

	public ExamDto getDtoFromExam(Exam exam) {
		ExamDto examDto = getSimpleDtoFromExam(exam);
		examDto.setTeacherDto(userService.getSimpleDtoFromTeacher(exam.getTeacher()));
		if (!exam.getStudentExams().isEmpty()) {
			examDto.setStudentExamDtos(exam.getStudentExams()
					.stream()
					.map(studentExamService::getDtoFromStudentExam)
					.collect(Collectors.toList()));
		}
		if (!exam.getExercises().isEmpty()) {
			examDto.setExerciseDtos(exam.getExercises()
					.stream()
					.map(exerciseService::getDtoFromExercise)
					.collect(Collectors.toList()));
		}
		return examDto;
	}

	public void updateExam(ExamDto examDto) {
		Exam exam = examRepository.getOne(examDto.getId());
		exam.setTitle(examDto.getTitle());
//		if (Objects.nonNull(examDto.getStudentDtos()) && !examDto.getStudentDtos().isEmpty()) {
//			exam.setStudents(examDto.getStudentDtos().stream()
//					.map(userService::createSimpleStudentFromDto)
//					.collect(Collectors.toList()));
//		}		
		exam.setDifficultyLevel(examDto.getDifficultyLevel());
		exam.setUpdated(LocalDateTime.now());
		if (!examDto.getExerciseDtos().isEmpty()) {
			exam.setExercises(examDto.getExerciseDtos().stream()
					.map(exerciseService::createSimpleExerciseFromDto).collect(Collectors.toList()));
		}		
		examRepository.save(exam);
	}
	
	public void saveExam(ExamDto examDto) {
		examRepository.save(createExamFromDto(examDto));
	}

	public ExamDto findExamById(Long id) {
		return getDtoFromExam(examRepository.getOne(id));
	}

	public List<ExamDto> findAllExams() {
		return examRepository.findAll()
			.stream()
			.map(this::getDtoFromExam)
			.collect(Collectors.toList());
	}

	public List<ExamDto> findAllExamsByTeacher(Long id) {
		return examRepository.findAllExamsByTeacherId(id)
			.stream()
			.map(this::getDtoFromExam)
			.collect(Collectors.toList());
	}
	
	public List<ExamDto> findLast5Exams(Long id) {
		return examRepository.findFirst5ExamsByTeacherIdOrderByCreatedDesc(id)
			.stream()
			.map(this::getDtoFromExam)
			.collect(Collectors.toList());
	}
	
//	public List<ExamDto> findAllExamsByStudentId(Long id) {
//		return examRepository.findAllExamsByStudentId(id)
//			.stream()
//			.map(this::getDtoFromExam)
//			.collect(Collectors.toList());
//	}

	public void deleteExam(Long id) {
		examRepository.delete(id);
	}
	
//	public String evaluateExam (Long studentId, ExamDto examDto) {
	public Integer evaluateExam (Long studentId, ExamDto examDto) {
		Exam exam = examRepository.getOne(examDto.getId());
		List<Exercise> exercises = exam.getExercises();
		List<ExerciseDto> exerciseDtos = examDto.getExerciseDtos();
		Integer result = 0;
		for (int i=0; i<exercises.size(); i++) {
			if(exercises.get(i).getAnswer().equals(exerciseDtos.get(i).getAnswer())) {
				result = result +1;
			}
		}
		studentExamService.saveExamResult(studentId, examDto.getId(), result);
//		return result + "/" + exercises.size();
		return result;
	}
	
}
