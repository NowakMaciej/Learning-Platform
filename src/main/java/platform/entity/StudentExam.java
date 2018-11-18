package platform.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="students_exams")
@Data
public class StudentExam implements Serializable {
	@EmbeddedId
	private StudentExamId id;
//	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("studentId")
	private Student student;
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("examId")
	private Exam exam;
	@Column(name = "result")
	private String result;
	
	public StudentExam (Student student, Exam exam) {
		this.student = student;
		this.exam = exam;
		this.id = new StudentExamId(student.getId(), exam.getId());
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StudentExam that = (StudentExam) o;
		return Objects.equals(student, that.student) && Objects.equals(exam, that.exam);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(student, exam);
	}
}


