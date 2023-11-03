package pl.kurs.personservice.factory.cteatorfromcsv;

import pl.kurs.personservice.model.Person;

public interface PersonFromCsvCreator {

    String getType();

    Person createPerson(String[] parameters);

}
