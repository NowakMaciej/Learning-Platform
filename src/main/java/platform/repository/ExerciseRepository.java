package platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import platform.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long>{
	List<Exercise> findAllExercisesByTeacherId(Long id);
	List<Exercise> findFirst5ExercisesByTeacherIdOrderByCreatedDesc(Long id);
}
