package pl.kurs.dictionaryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.dictionaryservice.model.Dictionary;

import java.util.Optional;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    @Query("SELECT d FROM Dictionary d LEFT JOIN FETCH d.dictionaryValues WHERE d.id = ?1")
    Optional<Dictionary> findByIdWithDictionaryValues(Long id);

}
