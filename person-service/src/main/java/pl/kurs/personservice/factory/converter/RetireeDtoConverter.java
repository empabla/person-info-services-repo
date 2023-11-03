package pl.kurs.personservice.factory.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.dto.PersonDto;
import pl.kurs.personservice.dto.RetireeDto;
import pl.kurs.personservice.model.Person;
import pl.kurs.personservice.model.Retiree;


@Service
@RequiredArgsConstructor
public class RetireeDtoConverter implements PersonDtoConverter {

    private final ModelMapper modelMapper;

    private final DictionaryServiceClient dictionaryServiceClient;

    @Override
    public String getType() {
        return "retiree";
    }

    @Override
    public PersonDto convert(Person person) {
        RetireeDto retireeDto = new RetireeDto();
        Retiree retiree = modelMapper.map(person, Retiree.class);
        retireeDto.setId(retiree.getId());
        retireeDto.setType(dictionaryServiceClient
                .getDictionaryValueById(retiree.getTypeId()).getName());
        retireeDto.setFirstName(retiree.getFirstName());
        retireeDto.setLastName(retiree.getLastName());
        retireeDto.setEmail(retiree.getEmail());
        retireeDto.setVersion(retiree.getVersion());
        retireeDto.setPension(retiree.getPension());
        retireeDto.setYearsOfWork(retiree.getYearsOfWork());
        return retireeDto;
    }

}
