package pl.kurs.dictionaryservice.mapping;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;
import pl.kurs.dictionaryservice.dto.DictionaryDto;
import pl.kurs.dictionaryservice.model.Dictionary;
import pl.kurs.dictionaryservice.model.DictionaryValue;

@Service
public class DictionaryToDictionaryDtoConverter implements Converter<Dictionary, DictionaryDto> {

    @Override
    public DictionaryDto convert(MappingContext<Dictionary, DictionaryDto> mappingContext) {
        Dictionary source = mappingContext.getSource();
        return DictionaryDto.builder()
                .id(source.getId())
                .name(source.getName())
                .dictionaryValues(source.getDictionaryValues()
                        .stream()
                        .map(DictionaryValue::getName)
                        .toArray(String[]::new))
                .build();
    }

}
