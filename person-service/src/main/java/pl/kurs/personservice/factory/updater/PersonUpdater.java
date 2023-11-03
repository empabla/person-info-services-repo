package pl.kurs.personservice.factory.updater;

import pl.kurs.personservice.command.UpdatePersonCommand;
import pl.kurs.personservice.model.Person;

public interface PersonUpdater {

    String getType();

    Person updatePerson(UpdatePersonCommand updateCommand);

}
