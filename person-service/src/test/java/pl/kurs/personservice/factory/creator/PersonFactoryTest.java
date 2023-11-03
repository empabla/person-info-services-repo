package pl.kurs.personservice.factory.creator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.kurs.personservice.PersonServiceApplication;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.command.CreateEmployeeCommand;
import pl.kurs.personservice.command.CreatePersonCommand;
import pl.kurs.personservice.dto.DictionaryValueSimpleDto;
import pl.kurs.personservice.model.Employee;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = PersonServiceApplication.class)
@ActiveProfiles("test")
public class PersonFactoryTest {

    @Mock
    private DictionaryServiceClient dictionaryServiceClient;

    private PersonFactory personFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        personFactory = new PersonFactory(Set.of(
                new EmployeeCreator(dictionaryServiceClient, new ModelMapper()),
                new StudentCreator(dictionaryServiceClient, new ModelMapper()),
                new RetireeCreator(dictionaryServiceClient, new ModelMapper())
        ), dictionaryServiceClient);
    }

    @Test
    public void shouldCreateNewEmployeeUsingPersonFactory() {
        //given
        DictionaryValueSimpleDto employee = new DictionaryValueSimpleDto(1L, "employee");
        DictionaryValueSimpleDto director = new DictionaryValueSimpleDto(2L, "director");
        CreatePersonCommand createCommand = new CreateEmployeeCommand(
                employee.getName(), "John", "Doe", "12345678911", 180, 70,
                "johndoe@test.com", LocalDate.of(2021, 1, 1),
                director.getId(), 100000.00
        );
        Mockito.when(dictionaryServiceClient
                .getDictionaryValueByDictionaryIdAndName(1L, employee.getName())).thenReturn(employee);
        Mockito.when(dictionaryServiceClient.getDictionaryValueById(director.getId())).thenReturn(director);
        //when
        Employee employeeToCreate = (Employee) personFactory.create(createCommand);
        //then
        assertNotNull(employeeToCreate);
        assertEquals("John", employeeToCreate.getFirstName());
        assertEquals("Doe", employeeToCreate.getLastName());
        assertEquals("12345678911", employeeToCreate.getPesel());
        assertEquals(180, employeeToCreate.getHeight());
        assertEquals(70, employeeToCreate.getWeight());
        assertEquals("johndoe@test.com", employeeToCreate.getEmail());
        assertEquals(LocalDate.of(2021, 1, 1), employeeToCreate.getEmploymentStartDate());
        assertEquals(director.getId(), employeeToCreate.getCurrentPositionId());
        assertEquals(100000.00, employeeToCreate.getCurrentSalary());
    }

}
