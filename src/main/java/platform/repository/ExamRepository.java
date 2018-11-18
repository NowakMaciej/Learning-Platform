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
	@Query("select e from Exam e join e.students s where s.id = ?1")
	List<Exam> findAllExamsByStudentId(Long id);
}
