package pl.kurs.personservice.factory.creator;

import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.command.CreatePersonCommand;
import pl.kurs.personservice.model.Person;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Getter
public class PersonFactory {

    private final Map<String, PersonCreator> creators;

    private final DictionaryServiceClient dictionaryServiceClient;

    public PersonFactory(Set<PersonCreator> creators, DictionaryServiceClient dictionaryServiceClient) {
        this.creators = creators.stream()
                .collect(Collectors.toMap(PersonCreator::getType, Function.identity()));
        this.dictionaryServiceClient = dictionaryServiceClient;
    }

    public Person create(CreatePersonCommand command) {
        return creators.get(dictionaryServiceClient.getDictionaryValueByDictionaryIdAndName(
                1L, command.getType().toLowerCase()).getName())
                .createPerson(command);
    }

}
