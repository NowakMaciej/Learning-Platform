package platform.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import platform.utils.DifficultyLevel;

@Entity
@Table(name="students")
@Data
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
//	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	@Enumerated(EnumType.STRING)
	private DifficultyLevel difficultyLevel;
//	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@ManyToOne
	private Teacher teacher;
//	@ManyToMany (mappedBy = "students", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@ManyToMany (mappedBy = "students")
	private List<Exam> exams = new ArrayList<>();
}


