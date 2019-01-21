package platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import platform.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	@Query("select s from Student s where s.user.active = true and s.teacher.id = :id")
	List<Student> findAllActiveStudentsByTeacherId(@Param("id") Long id);
	Student findStudentByUserId (Long id);
	@Query("select s from Student s where s.teacher.id is null")
//	@Query("select s from Student s where s.teacher.id is null AND s.user.active = false")
	List<Student> findAllUnassignedStudents();
}
