package pl.kurs.userservice.command;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kurs.userservice.validator.Password;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserCommand {

    @NotBlank
    private String username;

    @Password
    private String password;

}
