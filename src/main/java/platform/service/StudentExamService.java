package platform.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import platform.dto.ExamDto;
import platform.dto.ExerciseDto;
import platform.dto.StudentDto;
import platform.dto.StudentExamDto;
import platform.dto.TeacherDto;
import platform.entity.Exam;
import platform.entity.Exercise;
import platform.entity.StudentExam;
import platform.repository.ExamRepository;
import platform.repository.StudentExamRepository;
import platform.repository.TeacherRepository;

@Service
@Transactional
public class StudentExamService {
	private StudentExamRepository studentExamRepository;
	private ExamService examService;
	private UserServiceImpl userService;
	private ExamRepository examRepository;

	public StudentExamService(StudentExamRepository studentExamRepository, ExamService examService, UserServiceImpl userService, ExamRepository examRepository) {
		this.studentExamRepository = studentExamRepository;
		this.examService = examService;
		this.userService = userService;
		this.examRepository = examRepository;
	}
	
	public StudentExam createStudentExamFromDto (StudentExamDto studentExamDto) {
		StudentExam studentExam = new StudentExam();
		studentExam.setStudent(userService.createSimpleStudentFromDto(studentExamDto.getStudentDto()));
		studentExam.setExam(examService.createExamFromDto(studentExamDto.getExamDto()));
		studentExam.setActive(false);
		return studentExam;
	}
	
	public StudentExamDto getDtoFromStudentExam (StudentExam studentExam) {
		StudentExamDto studentExamDto = new StudentExamDto();
		studentExamDto.setId(studentExam.getId());
		studentExamDto.setStudentDto(userService.getDtoFromStudent(studentExam.getStudent()));
		studentExamDto.setExamDto(examService.getSimpleDtoFromExam(studentExam.getExam()));
//		studentExamDto.setExamDto(examService.getDtoFromExam(studentExam.getExam()));
		studentExamDto.setActive(studentExam.getActive());
		if(Objects.nonNull(studentExam.getResult())) {
			studentExamDto.setResult(studentExam.getResult());
		}
		return studentExamDto;
	}
	
	public void saveOrUpdate (List<StudentDto> listOfStudents, Long id) {
		ExamDto examDto = examService.findExamById(id);
		List<StudentExamDto> listOfStudentExamsFromDatabase = findAllStudentExamsByExamId(id);
		List<StudentDto> listOfStudentsFromDatabase = listOfStudentExamsFromDatabase.stream()
																					.map(StudentExamDto::getStudentDto)
																					.collect(Collectors.toList());
		if (!listOfStudents.isEmpty()){
			for (StudentDto studentDto : listOfStudents) {
				if (!listOfStudentsFromDatabase.contains(studentDto)){
					StudentExamDto studentExamDto = new StudentExamDto(studentDto, examDto);
					studentExamRepository.save(createStudentExamFromDto(studentExamDto));
				}
			}
		}
		if (!listOfStudentsFromDatabase.isEmpty()){
			for (StudentDto studentDto : listOfStudentsFromDatabase){
				StudentExam studentExam = studentExamRepository.findStudentExamByStudentIdAndExamId(studentDto.getId(),examDto.getId());
				if (!listOfStudents.contains(studentDto) && Objects.isNull(studentExam.getResult())){
					studentExamRepository.delete(studentExam.getId());
				}
			}
		}
	}
	
	public void saveExamResult(Long studentId, Long examId, Integer result){
		StudentExam studentExam = studentExamRepository.findStudentExamByStudentIdAndExamId(studentId, examId);
		studentExam.setResult(result);
		studentExam.setActive(false);
		studentExamRepository.save(studentExam);
	}
	
	public void toggleStudentExamActiveState (Long id) {
		Exam exam = examRepository.getOne(id);
		exam.setActive(!exam.getActive());
		examRepository.save(exam);
		studentExamRepository.findAllStudentExamsByExamId(id)
			.stream().
			forEach(studentExam -> { 
//				studentExam.setActive(!studentExam.getActive());
				studentExam.setActive(exam.getActive());
				});
		
		studentExamRepository.findAllStudentExamsByExamId(id)
		.stream().map(studentExamRepository::save);
	}
	
	public List<StudentExamDto> findAllStudentExamsByExamId(Long id) {
		return studentExamRepository.findAllStudentExamsByExamId(id)
				.stream()
				.map(this::getDtoFromStudentExam)
				.collect(Collectors.toList());
	}
	
	public List<StudentExamDto> findAllStudentExamsByStudentId(Long id) {
		return studentExamRepository.findAllStudentExamsByStudentId(id)
				.stream()
				.map(this::getDtoFromStudentExam)
				.collect(Collectors.toList());
	}
	
	public StudentExamDto findStudentExamByStudentIdAndExamId(Long studentId, Long examId) {
		return getDtoFromStudentExam(studentExamRepository.findStudentExamByStudentIdAndExamId(studentId, examId));
	}
	
	public StudentExamDto findStudentExamById (Long id){
		return getDtoFromStudentExam(studentExamRepository.getOne(id));
	}
}
