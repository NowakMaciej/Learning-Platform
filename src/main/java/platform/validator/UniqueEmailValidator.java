package platform.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import platform.dto.UserDto;
import platform.entity.User;
import platform.repository.UserRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{
//public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, UserDto>{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void initialize(UniqueEmail constraintAnnotation) {}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		User user = userRepository.findUserByEmail(value);
		return Objects.isNull(user);
	}
	
//	@Override
//	public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
//		String email = userDto.getEmail();
//		Long id = userDto.getId();
//		User user = userRepository.findUserByEmail(email);
//		System.out.println("LONG ID: " + id + "  STRING EMAIL: " + email);
//		if (Objects.nonNull(id)) {
//			System.out.println("USER_ID: " + user.getId() + " USER_EMAIL: " + user.getEmail());
//			if (id == user.getId()) {
//				return user.getEmail().equals(userDto.getEmail());
//			}
//			return true;
//		}
//		System.out.println(Objects.isNull(user));
//		return Objects.isNull(user);
//	}

}
// https://stackoverflow.com/questions/2781771/how-can-i-validate-two-or-more-fields-in-combination	
//https://www.baeldung.com/spring-mvc-custom-validator
