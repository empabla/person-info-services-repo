package pl.kurs.personservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.personservice.model.EmployeePosition;

import java.util.List;

public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {

    @Query("SELECT eóp FROM EmployeePosition ep WHERE ep.employee.id = :employeeId")
    List<EmployeePosition> findByEmployeeId(long employeeId);

}
