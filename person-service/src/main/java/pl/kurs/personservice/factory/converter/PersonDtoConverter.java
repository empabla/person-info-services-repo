package pl.kurs.personservice.factory.converter;

import pl.kurs.personservice.dto.PersonDto;
import pl.kurs.personservice.model.Person;

public interface PersonDtoConverter {

    String getType();

    PersonDto convert(Person person);

}
