package platform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import platform.dto.ExamDto;
import platform.dto.ExerciseDto;
import platform.dto.TeacherDto;
import platform.entity.Exam;
import platform.entity.Exercise;
import platform.repository.ExamRepository;
import platform.repository.TeacherRepository;

@Service
@Transactional
public class ExamService {
	private ExamRepository examRepository;
	private ExerciseService exerciseService;
	private TeacherRepository teacherRepository;
	private UserServiceImpl userService;

	public ExamService(ExamRepository examRepository, ExerciseService exerciseService, TeacherRepository teacherRepository,
			UserServiceImpl userService) {
		this.examRepository = examRepository;	
		this.exerciseService = exerciseService;
		this.teacherRepository = teacherRepository;
		this.userService = userService;
	}

	public Exam createExamFromDto(ExamDto examDto) {
		Exam exam = new Exam();
		if (examDto.getId() != null) {
			exam.setId(examDto.getId());
		}
		exam.setTitle(examDto.getTitle());
		exam.setTeacher(teacherRepository.getOne(examDto.getTeacherDto().getId()));
		if (!examDto.getStudentDtos().isEmpty()) {
			exam.setStudents(examDto.getStudentDtos().stream()
					.map(userService::createSimpleStudentFromDto).collect(Collectors.toList()));
		}		
		exam.setDifficultyLevel(examDto.getDifficultyLevel());
		exam.setCreated(LocalDateTime.now());
		exam.setUpdated(exam.getCreated());
		exam.setActive(true);
		if (!examDto.getExerciseDtos().isEmpty()) {
			exam.setExercises(examDto.getExerciseDtos().stream()
					.map(exerciseService::createSimpleExerciseFromDto).collect(Collectors.toList()));
		}		
		return exam;
	}
	
	public ExamDto getSimpleDtoFromExam(Exam exam) {
		ExamDto examDto = new ExamDto();
		examDto.setId(exam.getId());
		examDto.setTitle(exam.getTitle());
		examDto.setDifficultyLevel(exam.getDifficultyLevel());
		examDto.setCreated(exam.getCreated());
		examDto.setUpdated(exam.getUpdated());
		return examDto;
	}

	public ExamDto getDtoFromExam(Exam exam) {
		ExamDto examDto = getSimpleDtoFromExam(exam);
		examDto.setTeacherDto(userService.getDtoFromTeacher(exam.getTeacher()));
		if (!exam.getStudents().isEmpty()) {
			examDto.setStudentDtos(exam.getStudents()
					.stream()
					.map(userService::getSimpleDtoFromStudent)
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
		if (Objects.nonNull(examDto.getStudentDtos()) && !examDto.getStudentDtos().isEmpty()) {
			exam.setStudents(examDto.getStudentDtos().stream()
					.map(userService::createSimpleStudentFromDto)
					.collect(Collectors.toList()));
		}		
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
	
	public List<ExamDto> findAllExamsByStudentId(Long id) {
		return examRepository.findAllExamsByStudentId(id)
			.stream()
			.map(this::getDtoFromExam)
			.collect(Collectors.toList());
	}

	public void deleteExam(Long id) {
		examRepository.delete(id);
	}
	
	public String evaluateExam (ExamDto examDto) {
		Exam exam = examRepository.getOne(examDto.getId());
		List<Exercise> exercises = exam.getExercises();
		List<ExerciseDto> exerciseDtos = examDto.getExerciseDtos();
		Integer result = 0;
		for (int i=0; i<exercises.size(); i++) {
			if(exercises.get(i).getAnswer().equals(exerciseDtos.get(i).getAnswer())) {
				result = result +1;
			}
		}
		return result + "/" + exercises.size();
	}
}
