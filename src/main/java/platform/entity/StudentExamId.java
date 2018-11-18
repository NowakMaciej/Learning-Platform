package platform.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
//import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class StudentExamId implements Serializable{ // bez "implements..." nie działa
//	private static final long serialVersionUID = 1L; // usuwa warning który pojawia się po dodaniu "implements..."
	@Column (name = "studentId")
	private Long studentId;
	@Column (name = "examId")
	private Long examId;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StudentExamId that = (StudentExamId) o;
		return Objects.equals(studentId, that.studentId) && Objects.equals(examId, that.examId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(studentId, examId);
	}
}