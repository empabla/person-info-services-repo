package pl.kurs.personservice.factory.converter;

import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.dto.PersonDto;
import pl.kurs.personservice.model.Person;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Getter
public class PersonDtoConverterFactory {

    private final Map<String, PersonDtoConverter> converters;

    private final DictionaryServiceClient dictionaryServiceClient;

    public PersonDtoConverterFactory(Set<PersonDtoConverter> converters, DictionaryServiceClient dictionaryServiceClient) {
        this.converters = converters.stream()
                .collect(Collectors.toMap(PersonDtoConverter::getType, Function.identity()));
        this.dictionaryServiceClient = dictionaryServiceClient;
    }

    public PersonDto convert(Person person) {
        return converters.get(dictionaryServiceClient.getDictionaryValueById(person.getTypeId()).getName())
                .convert(person);
    }

}
