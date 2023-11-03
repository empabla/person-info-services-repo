package pl.kurs.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.userservice.model.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

}
