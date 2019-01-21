package platform.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="exercises")
@Data
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String text;
	private String answer;
	@ManyToOne
	private Teacher teacher;
	private LocalDateTime created;
	private LocalDateTime updated;
	private String difficultyLevel;
//	@ManyToMany(cascade = CascadeType.MERGE)
	@ManyToMany
//	private Set<Category> categories = new HashSet<>();
	private List<Category> categories = new ArrayList<>();
	@ManyToMany(mappedBy = "exercises")
	private List<Exam> exams = new ArrayList<>();
}

