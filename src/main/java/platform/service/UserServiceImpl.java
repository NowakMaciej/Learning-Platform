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
//	public void register(UserDto userDto) {
//		User user = userRepository.save(createUserFromDto(userDto));
//		userRepository.save(createUserFromDto(userDto));
		if (userDto.getRole().equals("student")) {
			studentRepository.save(createStudentFromDto(userDto));

		}
		if (userDto.getRole().equals("teacher")) {
			teacherRepository.save(createTeacherFromDto(userDto));
		}
		return userDto;
	}

//	public Boolean login(UserDto userDto) {	
//		User user = userRepository.findUserByEmail(userDto.getEmail());
//		if (Objects.isNull(user)) {
//			return false;
//		}
//		return BCrypt.checkpw(userDto.getPassword(), user.getPassword());
//	}

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

	public User createUserFromDto(UserDto userDto) {
		User user = new User();
		if (userDto.getId() != null) { // bez tego to chyba zapisuje nowego usera pod nowym id
			user.setId(userDto.getId());
		}
		user.setFirstname(userDto.getFirstname());
		user.setSurname(userDto.getSurname());
		user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		user.setEmail(userDto.getEmail());
		user.setActive(true);
		user.setRole(userDto.getRole());
		return user;
	}

	public Student createStudentFromDto(UserDto userDto) {
		Student student = new Student();
		student.setUser(createUserFromDto(userDto));
		student.setDifficultyLevel(userDto.getDifficultyLevel());
		if (Objects.nonNull(userDto.getTeacher())) {
			student.setTeacher(teacherRepository.findOne(userDto.getTeacher().getId()));
		}
		return student;
	}
	
	public Student createStudentFromDto(StudentDto studentDto) {
		System.out.println("STUDENT ID INFO POWER:" + studentDto.getTeacher().getId());
		Student student = new Student();
		student.setUser(createUserFromDto(studentDto.getUser()));
		student.setDifficultyLevel(studentDto.getUser().getDifficultyLevel());
		if (Objects.nonNull(studentDto.getUser().getTeacher())) {
//			student.setTeacher(teacherRepository.findOne(studentDto.getUser().getTeacher().getId()));
//			student.setTeacher(teacherRepository.findOne(studentDto.getTeacher().getUser().getId()));
			student.setTeacher(teacherRepository.findOne(studentDto.getTeacher().getId()));
		}
		return student;
	}
	
	public Student createSimpleStudentFromDto(StudentDto studentDto) {
		Student student = new Student();
		student.setId(studentDto.getId());
//		student.setUser(createUserFromDto(studentDto.getUser()));
		student.setDifficultyLevel(studentDto.getUser().getDifficultyLevel());
//		if (Objects.nonNull(studentDto.getUser().getTeacher())) {
//			student.setTeacher(teacherRepository.findOne(studentDto.getUser().getTeacher().getId()));
//			student.setTeacher(teacherRepository.findOne(studentDto.getTeacher().getUser().getId()));
//			student.setTeacher(teacherRepository.findOne(studentDto.getTeacher().getId()));
//		}
		return student;
	}

	public Teacher createTeacherFromDto(UserDto userDto) {
		Teacher teacher = new Teacher();
		teacher.setUser(createUserFromDto(userDto));
		return teacher;
	}

//	public Teacher createSimpleTeacherFromDto(UserDto userDto) {
//		Teacher teacher = new Teacher();
////		teacher.setUser(crea
//	}

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
		studentDto.setDifficultyLevel(student.getDifficultyLevel());
		return studentDto;
	}

	public StudentDto getDtoFromStudent(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setUser(getDtoFromUser(student.getUser()));
		studentDto.setDifficultyLevel(student.getDifficultyLevel());
		studentDto.setTeacher(getSimpleDtoFromTeacher(student.getTeacher()));
		return studentDto;

	}

	public TeacherDto getSimpleDtoFromTeacher(Teacher teacher) {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(teacher.getId());
		teacherDto.setUser(getDtoFromUser(teacher.getUser()));
		return teacherDto;
	}

	public TeacherDto getDtoFromTeacher(Teacher teacher) {
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(teacher.getId());
		teacherDto.setUser(getDtoFromUser(teacher.getUser()));
		if (!teacher.getStudents().isEmpty()) {
			teacherDto.setStudents(
					teacher.getStudents().stream().map(this::getSimpleDtoFromStudent).collect(Collectors.toList()));
		}
		return teacherDto;
	}

	public UserDto updateUser(UserDto userDto) {
		User user = userRepository.getOne(userDto.getId());
		user.setFirstname(userDto.getFirstname());
		user.setSurname(userDto.getSurname());
		user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		user.setEmail(userDto.getEmail());
		return getDtoFromUser(user);
	}

	public TeacherDto updateTeacher(TeacherDto teacherDto) {
		Teacher teacherForUpdate = teacherRepository.getOne(teacherDto.getId());
		teacherForUpdate.setUser(createUserFromDto(teacherDto.getUser()));
		teacherRepository.save(teacherForUpdate);
		return getDtoFromTeacher(teacherForUpdate);
	}

	public StudentDto updateStudent(StudentDto studentDto) {
		Student studentForUpdate = studentRepository.getOne(studentDto.getId());
		studentForUpdate.setUser(createUserFromDto(studentDto.getUser()));
		studentForUpdate.setDifficultyLevel(studentDto.getDifficultyLevel());
		studentForUpdate.setTeacher(teacherRepository.getOne(studentDto.getTeacher().getId()));
		studentRepository.save(studentForUpdate);
		return getDtoFromStudent(studentForUpdate);
	}

	public UserDto findUserById(Long id) {
		return getDtoFromUser(userRepository.getOne(id));
	}

	public UserDto findUserByEmail(String email) {
		return getDtoFromUser(userRepository.findUserByEmail(email));
	}

	public List<TeacherDto> findTeachers() {
		return teacherRepository.findAll()
				.stream()
				.map(this::getSimpleDtoFromTeacher)
				.collect(Collectors.toList());
	}

	public List<StudentDto> findAllStudentsByTeacherId(Long id) {
		return teacherRepository.findTeacherByUserId(id).getStudents()
				.stream()
				.map(this::getDtoFromStudent)
				.collect(Collectors.toList());
	}

	public TeacherDto findTeacherByUserId(Long id) {
		return getDtoFromTeacher(teacherRepository.findTeacherByUserId(id));
	}

	public StudentDto findStudentByUserId(Long id) {
		return getDtoFromStudent(studentRepository.findStudentByUserId(id));
	}
	
	public StudentDto findStudentById(Long id) {
		return getDtoFromStudent(studentRepository.getOne(id));
	}
	
	public TeacherDto findTeacherById(Long id) {
		return getDtoFromTeacher(teacherRepository.getOne(id));
	}

	public List<UserDto> findAllUsers() {
		return userRepository.findAll().stream().map(this::getDtoFromUser).collect(Collectors.toList());
	}

	public void deleteUser(Long id) {
		userRepository.delete(id);
	}
}
