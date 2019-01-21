package platform.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="students_exams")
@Data
public class StudentExam implements Serializable {
//	@EmbeddedId
//	private StudentExamId id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean active;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("studentId")
//	@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	private Student student;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@MapsId("examId")
//	@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	private Exam exam;
	
	@Column(name = "result")
	private Integer result;
	
	public StudentExam () {}
	
	public StudentExam (Student student, Exam exam) {
		this.student = student;
		this.exam = exam;
	}
	
//	public StudentExam (Student student, Exam exam) {
//		this.student = student;
//		this.exam = exam;
//		this.id = new StudentExamId(student.getId(), exam.getId());
//	}
//	
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		StudentExam that = (StudentExam) o;
//		return Objects.equals(student, that.student) && Objects.equals(exam, that.exam);
//	}
//	
//	@Override
//	public int hashCode() {
//		return Objects.hash(student, exam);
//	}
}


