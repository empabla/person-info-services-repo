package pl.kurs.personservice.factory.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.dto.EmployeeDto;
import pl.kurs.personservice.dto.PersonDto;
import pl.kurs.personservice.model.Employee;
import pl.kurs.personservice.model.Person;


@Service
@RequiredArgsConstructor
public class EmployeeDtoConverter implements PersonDtoConverter {

    private final ModelMapper modelMapper;

    private final DictionaryServiceClient dictionaryServiceClient;

    @Override
    public String getType() {
        return "employee";
    }

    @Override
    public PersonDto convert(Person person) {
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = modelMapper.map(person, Employee.class);
        employeeDto.setId(employee.getId());
        employeeDto.setType(dictionaryServiceClient
                .getDictionaryValueById(employee.getTypeId()).getName());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setVersion(employee.getVersion());
        employeeDto.setEmploymentStartDate(employee.getEmploymentStartDate());
        employeeDto.setCurrentPosition(dictionaryServiceClient
                .getDictionaryValueById(employee.getCurrentPositionId()).getName());
        employeeDto.setCurrentSalary(employee.getCurrentSalary());
        return employeeDto;
    }

}
