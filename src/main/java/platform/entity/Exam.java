package platform.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import platform.utils.DifficultyLevel;

@Entity
@Table(name="exams")
@Data
public class Exam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
//	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
//	@ManyToOne(cascade = { CascadeType.MERGE })
	@ManyToOne
	private Teacher teacher;
//	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
//	@ManyToMany(cascade = CascadeType.MERGE)
	@ManyToMany
	private List<Student> students = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private DifficultyLevel difficultyLevel;
	private LocalDateTime created;
	private LocalDateTime updated;
	private Boolean active;
//	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
//	@ManyToMany(cascade = CascadeType.MERGE)
	@ManyToMany
	private List<Exercise> exercises = new ArrayList<>();
}

