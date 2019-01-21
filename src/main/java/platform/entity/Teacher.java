package platform.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="teachers")
@Data
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL)
//	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(mappedBy = "teacher")
	private List<Student> students = new ArrayList<>();
	@OneToMany (mappedBy = "teacher")
//	private Set<Category> categories = new HashSet<>();
	private List<Category> categories = new ArrayList<>();
	@OneToMany (mappedBy = "teacher")
	private List<Exam> exams = new ArrayList<>();
}

