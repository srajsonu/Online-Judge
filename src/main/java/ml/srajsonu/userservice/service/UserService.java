package ml.srajsonu.userservice.service;

import ml.srajsonu.userservice.dto.UserDto;
import ml.srajsonu.userservice.dto.UserResponseDto;
import ml.srajsonu.userservice.model.User;

public interface UserService {

    public User registerUser(UserDto userDto);
}
