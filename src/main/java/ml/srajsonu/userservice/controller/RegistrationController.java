package ml.srajsonu.userservice.controller;

import ml.srajsonu.userservice.dto.ResponseDto;
import ml.srajsonu.userservice.dto.UserDto;
import ml.srajsonu.userservice.dto.UserResponseDto;
import ml.srajsonu.userservice.model.User;
import ml.srajsonu.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseDto<UserResponseDto> registerUSer(@RequestBody UserDto userDto) {

        User user = userService.registerUser(userDto);

        return new ResponseDto<>(
                new UserResponseDto(user.getId(), user.getFullName(), user.getEmail(), user.getIsActive()),
                HttpStatus.OK
        );
    }
}
