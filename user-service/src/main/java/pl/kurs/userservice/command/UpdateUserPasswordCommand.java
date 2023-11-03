package pl.kurs.userservice.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kurs.userservice.validator.Password;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPasswordCommand {

    @Password
    private String password;

}
