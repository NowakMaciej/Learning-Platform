package platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import platform.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	List<Student> findAllStudentsByTeacherId(Long id);
	Student findStudentByUserId (Long id);
	@Query("select s from Student s where s.teacher.id is null")
//	@Query("select s from Student s where s.teacher.id is null AND s.user.active = false")
	List<Student> findAllUnassignedStudents();
}
