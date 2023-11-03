package pl.kurs.personservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.personservice.api.DictionaryServiceClient;
import pl.kurs.personservice.command.CreateEmployeePositionCommand;
import pl.kurs.personservice.command.UpdateEmployeePositionEndDateCommand;
import pl.kurs.personservice.exception.IllegalEmploymentDateException;
import pl.kurs.personservice.exception.InvalidEntityException;
import pl.kurs.personservice.exception.InvalidIdException;
import pl.kurs.personservice.exception.PositionNotBelongToEmployeeException;
import pl.kurs.personservice.model.Employee;
import pl.kurs.personservice.model.EmployeePosition;
import pl.kurs.personservice.repository.EmployeePositionRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeePositionService {

    private final EmployeePositionRepository employeePositionRepository;

    private final EmployeeService employeeService;

    private final ModelMapper modelMapper;

    private final DictionaryServiceClient dictionaryServiceClient;

    @Transactional
    public EmployeePosition addPositionToEmployee(Long employeeId, CreateEmployeePositionCommand command) {
        Employee employee = employeeService.getById(employeeId);
        String newPositionName = dictionaryServiceClient.getDictionaryValueById(command.getPositionId()).getName();
        Long newPositionId = dictionaryServiceClient
                .getDictionaryValueByDictionaryIdAndName(2L, newPositionName).getId();
        List<EmployeePosition> existingPositions = employeePositionRepository.findByEmployeeId(employeeId);
        boolean allPositionsHaveEndDate = existingPositions.stream()
                .allMatch(existingPosition -> existingPosition.getEndDate() != null);
        if (!allPositionsHaveEndDate)
            throw new IllegalEmploymentDateException(
                    "Not all existing employee's positions have an end date. " +
                            "To add new position all previous must be over."
            );
        boolean isStartDateValid = command.getStartDate().isAfter(employee.getEmploymentStartDate());
        if (!isStartDateValid)
            throw new IllegalEmploymentDateException(
                    "Start date of the new position cannot be before employee's " + "employment start date: "
                            + employee.getEmploymentStartDate()
            );
        boolean isOverlap = existingPositions.stream()
                .anyMatch(existingPosition -> !command.getStartDate().isAfter(existingPosition.getEndDate()));
        if (isOverlap)
            throw new IllegalEmploymentDateException("New position overlaps with an existing one.");
        EmployeePosition employeePosition = modelMapper.map(command, EmployeePosition.class);
        employeePosition.setEmployee(employee);
        employeePosition.setPositionId(newPositionId);
        employee.setCurrentPositionId(newPositionId);
        employee.setCurrentSalary(command.getSalary());
        return add(employeePosition);
    }

    @Transactional
    public EmployeePosition updateEndDateForCurrentPosition(
            Long employeeId, Long positionId, UpdateEmployeePositionEndDateCommand command) {
        EmployeePosition currentPosition = getEmployeePositionById(employeeId, positionId);
        currentPosition.setEndDate(command.getEndDate());
        return employeePositionRepository.save(currentPosition);
    }

    @Transactional(readOnly = true)
    public List<EmployeePosition> getEmployeePositions(Long employeeId) {
        employeeService.getById(employeeId);
        return employeePositionRepository.findByEmployeeId(employeeId);
    }

    @Transactional(readOnly = true)
    public EmployeePosition getEmployeePositionById(Long employeeId, Long positionId) {
        EmployeePosition currentPosition = getById(positionId);
        if (!currentPosition.getEmployee().getId().equals(employeeId)) {
            throw new PositionNotBelongToEmployeeException(
                    "Position with id " + positionId + " does not belong " + "to the employee with id " + employeeId
            );
        }
        return currentPosition;
    }

    public void deleteById(Long employeeId, Long positionId) {
        EmployeePosition positionToDelete = getById(positionId);
        if (!positionToDelete.getEmployee().getId().equals(employeeId)) {
            throw new PositionNotBelongToEmployeeException(
                    "Position with id " + positionId + " does not belong " + "to the employee with id " + employeeId
            );
        }
        employeePositionRepository.delete(positionToDelete);
    }

    public EmployeePosition getById(Long id) {
        return employeePositionRepository.findById(
                Optional.ofNullable(id)
                        .orElseThrow(() -> new InvalidIdException("Wrong id."))
        ).orElseThrow(() -> new EntityNotFoundException("Employee position with id " + id + " not found."));
    }

    public EmployeePosition add(EmployeePosition employeePosition) {
        return employeePositionRepository.save(
                Optional.ofNullable(employeePosition)
                        .filter(x -> Objects.isNull(x.getId()))
                        .orElseThrow(() -> new InvalidEntityException("Wrong entity for persist."))
        );
    }

}
