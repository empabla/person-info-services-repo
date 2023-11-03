package pl.kurs.personservice.factory.specification;

import org.springframework.data.jpa.domain.Specification;
import pl.kurs.personservice.model.Person;

import java.util.Map;

public interface SearchSpecification<T extends Person> {

    String getType();

    Specification<Person> createSpecification(Map<String, String> parameters);

}
