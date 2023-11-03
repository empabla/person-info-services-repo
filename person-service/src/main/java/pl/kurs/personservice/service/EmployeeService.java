package pl.kurs.personservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kurs.personservice.exception.InvalidIdException;
import pl.kurs.personservice.exception.PersonNotFoundException;
import pl.kurs.personservice.model.Employee;
import pl.kurs.personservice.repository.EmployeeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee getById(Long id) {
        return employeeRepository.findById(
                Optional.ofNullable(id)
                        .orElseThrow(() -> new InvalidIdException("Wrong id."))
        ).orElseThrow(() -> new PersonNotFoundException("Employee with id " + id + " not found."));
    }

}
