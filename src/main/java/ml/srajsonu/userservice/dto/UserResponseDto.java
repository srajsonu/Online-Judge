package ml.srajsonu.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;

    private String fullName;

    private String email;

    private Boolean isActive;

    public UserResponseDto(Long id, String fullName, String email, Boolean isActive) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.isActive = isActive;
    }
}
