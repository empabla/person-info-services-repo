package pl.kurs.personservice.factory.creator;


import pl.kurs.personservice.command.CreatePersonCommand;
import pl.kurs.personservice.model.Person;

public interface PersonCreator {

    String getType();

    Person createPerson(CreatePersonCommand createCommand);

}
