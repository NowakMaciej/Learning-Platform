package platform.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstname;
	private String surname;
	private String email;
	private String password;
	private String role;
	private Boolean active;
	@OneToMany (mappedBy = "sender", cascade = CascadeType.ALL)
	private List<Message> messagesSent;
	@OneToMany (mappedBy = "receiver", cascade = CascadeType.ALL)
	private List<Message> messagesReceived;
}
