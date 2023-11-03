package pl.kurs.personservice.factory.creator;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.command.CreatePersonCommand;
import pl.kurs.personservice.command.CreateRetireeCommand;
import pl.kurs.personservice.model.Person;
import pl.kurs.personservice.model.Retiree;

@Service
@RequiredArgsConstructor
public class RetireeCreator implements PersonCreator {

    private final DictionaryServiceClient dictionaryServiceClient;

    private final ModelMapper modelMapper;

    @Override
    public String getType() {
        return "retiree";
    }

    @Override
    public Person createPerson(CreatePersonCommand createPersonCommand) {
        CreateRetireeCommand retireeCommand = modelMapper.map(createPersonCommand, CreateRetireeCommand.class);
        return new Retiree(
                dictionaryServiceClient
                        .getDictionaryValueByDictionaryIdAndName(1L, createPersonCommand.getType()).getId(),
                retireeCommand.getFirstName(),
                retireeCommand.getLastName(),
                retireeCommand.getPesel(),
                retireeCommand.getHeight(),
                retireeCommand.getWeight(),
                retireeCommand.getEmail(),
                retireeCommand.getPension(),
                retireeCommand.getYearsOfWork()
        );
    }

}
