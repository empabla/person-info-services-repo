package pl.kurs.personservice.factory.cteatorfromcsv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.model.Person;
import pl.kurs.personservice.model.Retiree;

@Service
@RequiredArgsConstructor
public class RetireeFromCsvCreator implements PersonFromCsvCreator {

    private final DictionaryServiceClient dictionaryServiceClient;

    @Override
    public String getType() {
        return "retiree";
    }

    @Override
    public Person createPerson(String[] parameters) {
        return new Retiree(
                dictionaryServiceClient.getDictionaryValueByDictionaryIdAndName(
                        1L, parameters[0].toLowerCase()).getId(),
                parameters[1].trim(),
                parameters[2].trim(),
                parameters[3].trim(),
                Integer.parseInt(parameters[4].trim()),
                Integer.parseInt(parameters[5].trim()),
                parameters[6].trim(),
                Double.parseDouble(parameters[7].trim()),
                Integer.parseInt(parameters[8].trim())
        );
    }

}
