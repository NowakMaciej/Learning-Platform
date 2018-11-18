package platform.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import platform.dto.StudentDto;
import platform.dto.TeacherDto;
import platform.dto.UserDto;
import platform.entity.Student;
import platform.entity.Teacher;
import platform.entity.User;
import platform.repository.StudentRepository;
import platform.repository.TeacherRepository;
import platform.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl {
	private UserRepository userRepository;
	private TeacherRepository teacherRepository;
	private StudentRepository studentRepository;

	public UserServiceImpl(UserRepository userRepository, @Lazy TeacherRepository teacherRepository,
			@Lazy StudentRepository studentRepository) {
		this.userRepository = userRepository;
		this.teacherRepository = teacherRepository;
		this.studentRepository = studentRepository;
	}

	public UserDto register(UserDto userDto) {
		if (userDto.getRole().equals("student")) {
			System.out.println(userDto);
			studentRepository.save(createStudentFromDto(userDto));
		}
		else if (userDto.getRole().equals("teacher")) {
			teacherRepository.save(createTeacherFromDto(userDto));
		}
		else {
			userRepository.save(createUserFromDto(userDto));
		}
		return userDto;
	}

	public UserDto login (UserDto userDto) {
		if (Objects.nonNull(userRepository.findUserByEmail(userDto.getEmail()))){
			User user = userRepository.findUserByEmail(userDto.getEmail());
			if(BCrypt.checkpw(userDto.getPassword(), user.getPassword())) {
				return getDtoFromUser(user);
			}
			return null;
		}
		return null;
	}
	
	public void changeActive (Long id) {
		User user = userRepository.findOne(id);
		user.setActive(!user.getActive());
		userRepository.save(user);
	}

	public User createUserFromDto(UserDto userDto) {
		User user = new User();
		if (userDto.getId() != null) { // bez tego to chyba zapisuje nowego usera pod nowym id
			user.setId(userDto.getId());
		}
		user.setFirstname(userDto.getFirstname());
		user.setSurname(userDto.getSurname());
		user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		user.setEmail(userDto.getEmail());
		if (Objects.isNull(userDto.getActive())) {
			user.setActive(false);
		}
		if (Objects.nonNull(userDto.getRole())) {
			user.setRole(userDto.getRole());
		}
		return user;
	}
	
	public Teacher createTeacherFromDto(UserDto userDto) {
		Teacher teacher = new Teacher();
		teacher.setUser(createUserFromDto(userDto));
		return teacher;
	}

	public Student createStudentFromDto(UserDto userDto) {
		Student student = new Student();
		student.setUser(createUserFromDto(userDto));
		if (Objects.nonNull(userDto.getDifficultyLevel())) {
			student.setDifficultyLevel(userDto.getDifficultyLevel());
		}
		if (Objects.nonNull(userDto.getTeacher())) {
			student.setTeacher(teacherRepository.findOne(userDto.getTeacher().getId()));
		}
		return student;
	}
	
	public Student createSimpleStudentFromDto(StudentDto studentDto) {
		Student student = new Student();
		student.setId(studentDto.getId());
		return student;
	}

	public UserDto getDtoFromUser(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstname(user.getFirstname());
		userDto.setSurname(user.getSurname());
		userDto.setEmail(user.getEmail());
//		userDto.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		userDto.setRole(user.getRole());
		userDto.setActive(user.getActive());
		return userDto;
	}

	public StudentDto getSimpleDtoFromStudent(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setUser(getDtoFromUser(student.getUser()));
		if (Objects.nonNull(student.getDifficultyLevel())) {
			studentDto.setDifficultyLevel(student.getDifficultyLevel());
		}
		return studentDto;
	}

	public StudentDto getDtoFromStudent(Student student) {
		StudentDto studentDto = getSimpleDtoFromStudent(student);
		if(Objects.nonNull(student.getTeacher())) {
			studentDto.setTeacher(getSimpleDtoFromTeacher(student.getTeacher()));
		}
		return studentDto;
	}

	public TeacherDto getSimpleDtoFromTeacher(Teacher teacher) {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(teacher.getId());
		teacherDto.setUser(getDtoFromUser(teacher.getUser()));
		return teacherDto;
	}

	public TeacherDto getDtoFromTeacher(Teacher teacher) {
		TeacherDto teacherDto = getSimpleDtoFromTeacher(teacher);
		if (!teacher.getStudents().isEmpty()) {
			teacherDto.setStudents(
					teacher.getStudents()
					.stream()
					.map(this::getSimpleDtoFromStudent)
					.collect(Collectors.toList()));
		}
		return teacherDto;
	}
	
	public User updateUser(UserDto userDto) {
		User user = userRepository.getOne(userDto.getId());
		user.setFirstname(userDto.getFirstname());
		user.setSurname(userDto.getSurname());
		user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		user.setEmail(userDto.getEmail());
		return user;
	}

	public TeacherDto updateTeacher(TeacherDto teacherDto) {
		Teacher teacherForUpdate = teacherRepository.getOne(teacherDto.getId());
		teacherForUpdate.setUser(createUserFromDto(teacherDto.getUser()));
		teacherRepository.save(teacherForUpdate);
		return getDtoFromTeacher(teacherForUpdate);
	}

	public StudentDto updateStudent(StudentDto studentDto) {
		Student studentForUpdate = studentRepository.getOne(studentDto.getId());
		studentForUpdate.setUser(updateUser(studentDto.getUser()));
		if (Objects.nonNull(studentDto.getDifficultyLevel())){
			studentForUpdate.setDifficultyLevel(studentDto.getDifficultyLevel());
		}
		if (Objects.nonNull(studentDto.getTeacher())){
			studentForUpdate.setTeacher(teacherRepository.getOne(studentDto.getTeacher().getId()));
		}
		studentRepository.save(studentForUpdate);
		return getDtoFromStudent(studentForUpdate);
	}

	public UserDto findUserById(Long id) {
		return getDtoFromUser(userRepository.getOne(id));
	}

	public UserDto findUserByEmail(String email) {
		return getDtoFromUser(userRepository.findUserByEmail(email));
	}
	
	public StudentDto findStudentById(Long id) {
		return getDtoFromStudent(studentRepository.getOne(id));
	}

	public StudentDto findStudentByUserId(Long id) {
		return getDtoFromStudent(studentRepository.findStudentByUserId(id));
	}
	
	public TeacherDto findTeacherById(Long id) {
		return getDtoFromTeacher(teacherRepository.getOne(id));
	}
	
	public TeacherDto findTeacherByUserId(Long id) {
		return getDtoFromTeacher(teacherRepository.findTeacherByUserId(id));
	}
	
	public List<StudentDto> findAllUnassignedStudents() {
		return studentRepository.findAllUnassignedStudents()
			.stream()
			.map(this::getDtoFromStudent)
			.collect(Collectors.toList());
	}

	public List<TeacherDto> findAllTeachers() {
		return teacherRepository.findAll()
				.stream()
				.map(this::getDtoFromTeacher)
				.collect(Collectors.toList());
	}
	
	public List<StudentDto> findAllStudents() {
		return studentRepository.findAll()
				.stream()
				.map(this::getDtoFromStudent)
				.collect(Collectors.toList());
	}
	
	public List<StudentDto> findAllStudentsByTeacherId(Long id) {
		return studentRepository.findAllStudentsByTeacherId(id)
				.stream()
				.map(this::getDtoFromStudent)
				.collect(Collectors.toList());
	}

	public void deleteUser(Long id) {
		userRepository.delete(id);
	}
}
