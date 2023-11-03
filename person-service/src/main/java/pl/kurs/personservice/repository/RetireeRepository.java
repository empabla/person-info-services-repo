package pl.kurs.personservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.personservice.model.Retiree;

public interface RetireeRepository extends JpaRepository<Retiree, Long> {

}
