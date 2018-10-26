package platform.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "categories")
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDateTime created;
	private LocalDateTime updated;
	@ManyToOne (cascade = { CascadeType.MERGE, CascadeType.PERSIST })
//	@ManyToOne (cascade =  CascadeType.MERGE)
	private Teacher teacher;
	@ManyToMany (mappedBy = "categories")
	private List<Exercise> exercises = new ArrayList<>();
}
