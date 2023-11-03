package pl.kurs.personservice.factory.creator;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.command.CreatePersonCommand;
import pl.kurs.personservice.command.CreateStudentCommand;
import pl.kurs.personservice.model.Person;
import pl.kurs.personservice.model.Student;

@Service
@RequiredArgsConstructor
public class StudentCreator implements PersonCreator {

    private final DictionaryServiceClient dictionaryServiceClient;

    private final ModelMapper modelMapper;

    @Override
    public String getType() {
        return "student";
    }

    @Override
    public Person createPerson(CreatePersonCommand createPersonCommand) {
        CreateStudentCommand studentCommand = modelMapper.map(createPersonCommand, CreateStudentCommand.class);
        return new Student(
                dictionaryServiceClient
                        .getDictionaryValueByDictionaryIdAndName(1L, createPersonCommand.getType()).getId(),
                studentCommand.getFirstName(),
                studentCommand.getLastName(),
                studentCommand.getPesel(),
                studentCommand.getHeight(),
                studentCommand.getWeight(),
                studentCommand.getEmail(),
                dictionaryServiceClient.getDictionaryValueById(studentCommand.getUniversityNameId()).getId(),
                studentCommand.getEnrollmentYear(),
                dictionaryServiceClient.getDictionaryValueById(studentCommand.getFieldOfStudyId()).getId(),
                studentCommand.getScholarship()
        );
    }

}
