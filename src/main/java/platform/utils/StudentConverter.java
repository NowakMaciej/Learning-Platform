package platform.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import platform.dto.StudentDto;
import platform.service.UserServiceImpl;

public class StudentConverter implements Converter<String, StudentDto>{
	@Autowired
	UserServiceImpl userService;

	@Override
	public StudentDto convert(String source) {
		return this.userService.findStudentById(Long.parseLong(source));
	}

}
