package pl.kurs.personservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.personservice.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
