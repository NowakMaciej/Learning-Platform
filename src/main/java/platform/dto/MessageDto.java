package platform.dto;

import lombok.Data;

@Data
public class MessageDto {
	private Long id;
	private String text;
	private Boolean isRead;
	private UserDto sender;
	private UserDto receiver;
}
