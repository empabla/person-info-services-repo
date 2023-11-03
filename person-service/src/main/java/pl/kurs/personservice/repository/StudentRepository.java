package pl.kurs.personservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.personservice.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
