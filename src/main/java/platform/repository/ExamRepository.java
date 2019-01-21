package platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import platform.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long>{
	List<Exam> findAllExamsByTeacherId(Long id);
	List<Exam> findFirst5ExamsByTeacherIdOrderByCreatedDesc(Long id);
//	@Query("select e from Exam e join e.students s where s.id = ?1")
//	List<Exam> findAllExamsByStudentId(Long id);
}

//	page exam2 line 39/40
//	Students:<br>
//	<form:select path="studentDtos" items="${students}" itemValue="id" itemLabel="name"/><br>

//	page teacher line 76
//	<td>${ lastExam.studentDtos.size() }</td>

//	page exam1 line 55
//	<td>${ exam.studentDtos.size() }</td>
