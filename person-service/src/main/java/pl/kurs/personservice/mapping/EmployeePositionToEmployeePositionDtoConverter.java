package pl.kurs.personservice.mapping;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.dto.EmployeePositionDto;
import pl.kurs.personservice.model.EmployeePosition;


@Service
@RequiredArgsConstructor
public class EmployeePositionToEmployeePositionDtoConverter implements Converter<EmployeePosition, EmployeePositionDto> {

    private final DictionaryServiceClient dictionaryServiceClient;

    @Override
    public EmployeePositionDto convert(MappingContext<EmployeePosition, EmployeePositionDto> mappingContext) {
        EmployeePosition source = mappingContext.getSource();
        return EmployeePositionDto.builder()
                .position(dictionaryServiceClient.getDictionaryValueById(source.getPositionId()).getName())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .salary(source.getSalary())
                .build();
    }

}
