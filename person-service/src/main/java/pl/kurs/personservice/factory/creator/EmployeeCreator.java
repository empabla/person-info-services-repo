package pl.kurs.personservice.factory.creator;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.command.CreateEmployeeCommand;
import pl.kurs.personservice.command.CreatePersonCommand;
import pl.kurs.personservice.model.Employee;
import pl.kurs.personservice.model.Person;

@Service
@RequiredArgsConstructor
public class EmployeeCreator implements PersonCreator {

    private final DictionaryServiceClient dictionaryServiceClient;

    private final ModelMapper modelMapper;

    @Override
    public String getType() {
        return "employee";
    }

    @Override
    public Person createPerson(CreatePersonCommand createPersonCommand) {
        CreateEmployeeCommand employeeCommand = modelMapper.map(createPersonCommand, CreateEmployeeCommand.class);
        return new Employee(
                dictionaryServiceClient
                        .getDictionaryValueByDictionaryIdAndName(1L, createPersonCommand.getType()).getId(),
                employeeCommand.getFirstName(),
                employeeCommand.getLastName(),
                employeeCommand.getPesel(),
                employeeCommand.getHeight(),
                employeeCommand.getWeight(),
                employeeCommand.getEmail(),
                employeeCommand.getEmploymentStartDate(),
                dictionaryServiceClient.getDictionaryValueById(employeeCommand.getCurrentPositionId()).getId(),
                employeeCommand.getCurrentSalary()
        );
    }

}
