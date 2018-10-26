package platform.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="messages")
@Data
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String text;
	private Boolean isRead;
	@ManyToOne (cascade = CascadeType.ALL)
	private Teacher teacher;
	@ManyToOne (cascade = CascadeType.ALL)
	private Student student;
	@ManyToOne (cascade = CascadeType.ALL)
	private User sender;
	@ManyToOne (cascade = CascadeType.ALL)
	private User receiver;
}
