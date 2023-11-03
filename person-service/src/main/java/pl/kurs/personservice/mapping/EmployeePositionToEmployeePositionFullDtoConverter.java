package pl.kurs.personservice.mapping;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.dto.EmployeePositionFullDto;
import pl.kurs.personservice.dto.PersonSimpleDto;
import pl.kurs.personservice.model.EmployeePosition;
import pl.kurs.personservice.model.Person;


@Service
@RequiredArgsConstructor
public class EmployeePositionToEmployeePositionFullDtoConverter implements
        Converter<EmployeePosition, EmployeePositionFullDto> {

    private final DictionaryServiceClient dictionaryServiceClient;

    @Override
    public EmployeePositionFullDto convert(MappingContext<EmployeePosition, EmployeePositionFullDto> mappingContext) {
        EmployeePosition source = mappingContext.getSource();
        Person employee = source.getEmployee();
        PersonSimpleDto personSimpleDto = PersonSimpleDto.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .build();
        return EmployeePositionFullDto.builder()
                .personSimpleDto(personSimpleDto)
                .position(dictionaryServiceClient.getDictionaryValueById(source.getPositionId()).getName())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .salary(source.getSalary())
                .build();
    }

}
