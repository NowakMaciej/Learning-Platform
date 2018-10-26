package platform.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import platform.dto.MessageDto;
import platform.dto.UserDto;
import platform.entity.Message;
import platform.repository.MessageRepository;
import platform.repository.UserRepository;

@Service
@Transactional
public class MessageService {
	private MessageRepository messageRepository;
	private UserRepository userRepository;
	private UserServiceImpl userService;
	
	public MessageService(MessageRepository messageRepository,  @Lazy UserRepository userRepository, @Lazy UserServiceImpl userService) {
		this.messageRepository = messageRepository;
		this.userRepository = userRepository;
		this.userService = userService;
	}
	
	public Message createMessageFromDto(MessageDto messageDto, UserDto userDto) {
		Message message = new Message();
		message.setText(messageDto.getText());
		message.setIsRead(false);
		message.setSender(userRepository.findUserByEmail(userDto.getEmail()));
		message.setReceiver(userRepository.getOne(messageDto.getReceiver().getId()));
		return message;
	}
	
	public MessageDto getDtoFromMessage(Message message) {
		MessageDto messageDto = new MessageDto();
		messageDto.setId(message.getId());
		messageDto.setText(message.getText());
		messageDto.setIsRead(message.getIsRead());
		messageDto.setSender(userService.getDtoFromUser(message.getSender()));
		messageDto.setReceiver(userService.getDtoFromUser(message.getReceiver()));
		return messageDto;
	}
	
	public void saveMessage (MessageDto messageDto, UserDto userDto){
		messageRepository.save(createMessageFromDto(messageDto, userDto));
	}
	
//	public void markMessageAsRead (Long id){
	public void markMessageAsRead (Long id, UserDto user){
		Message message = messageRepository.findOne(id);
		if (!message.getIsRead() && user.getId() == message.getReceiver().getId()) {
//		if (!message.getIsRead()) {
			message.setIsRead(true);
			messageRepository.save(message);
		}
	}
	
	public MessageDto findMessage(Long id) {
		return getDtoFromMessage(messageRepository.getOne(id));
	}
	
	public void deleteMessage(Long id){
		messageRepository.delete(id);
	}
	public List<MessageDto> findAllMessagesBySenderId(Long id) {
		return messageRepository.findAllMessagesBySenderId(id)
				.stream()
				.map(this::getDtoFromMessage)
				.collect(Collectors.toList());
	}
	
	public List<MessageDto> findAllMessagesByReceiverId(Long id) {
		return messageRepository.findAllMessagesByReceiverId(id)
				.stream()
				.map(this::getDtoFromMessage)
				.collect(Collectors.toList());
	}
	
}
