package pl.kurs.personservice.command;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateRetireeCommand extends CreatePersonCommand {

    @PositiveOrZero(message = "Cannot be null; must be positive")
    private Double pension;

    @PositiveOrZero(message = "Cannot be null; must be positive")
    private Integer yearsOfWork;

}
