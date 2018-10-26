package platform.service;

import platform.dto.UserDto;

public interface UserService {
	UserDto register (UserDto userDto);
	Boolean login (UserDto userDto);
}
