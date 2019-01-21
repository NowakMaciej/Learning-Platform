package platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import platform.entity.StudentExam;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam, Long> {
	List<StudentExam> findAllStudentExamsByExamId(Long id);
	List<StudentExam> findAllStudentExamsByStudentId(Long id);
//	List<StudentExam> findStudentExamByStudentId(Long id);
	StudentExam findStudentExamByStudentIdAndExamId(Long studentId, Long examId);
}
