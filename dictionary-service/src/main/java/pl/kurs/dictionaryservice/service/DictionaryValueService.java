package pl.kurs.dictionaryservice.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.dictionaryservice.command.CreateDictionaryValueCommand;
import pl.kurs.dictionaryservice.command.UpdateDictionaryValueCommand;
import pl.kurs.dictionaryservice.exception.DictionaryValueNotFoundException;
import pl.kurs.dictionaryservice.exception.InvalidIdException;
import pl.kurs.dictionaryservice.model.DictionaryValue;
import pl.kurs.dictionaryservice.repository.DictionaryValueRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DictionaryValueService {

    private final DictionaryValueRepository dictionaryValueRepository;

    @Transactional(readOnly = true)
    @Cacheable("dictionaryValues")
    public DictionaryValue getDictionaryValueById(Long id) {
        return dictionaryValueRepository.findById(
                Optional.ofNullable(id)
                        .orElseThrow(() -> new InvalidIdException("Id cannot be null."))
        ).orElseThrow(() -> new DictionaryValueNotFoundException("Dictionary value with id " + id + " not found."));
    }

    @Transactional(readOnly = true)
    public List<DictionaryValue> getAllDictionaryValues() {
        return dictionaryValueRepository.findAll();
    }

    @Transactional(readOnly = true)
    public DictionaryValue getDictionaryValueByDictionaryIdAndName(Long dictionaryId, String name) {
        return dictionaryValueRepository.findByDictionaryIdAndName(dictionaryId, name)
                .orElseThrow(() -> new DictionaryValueNotFoundException(
                        "Dictionary value '" + name + "' not found in the dictionary with id " + dictionaryId));
    }

    @Transactional
    public DictionaryValue addDictionaryValue(CreateDictionaryValueCommand command) {
        DictionaryValue dictionaryValueForSave = new DictionaryValue();
        dictionaryValueForSave.setName(command.getName().toLowerCase());
        return dictionaryValueRepository.save(dictionaryValueForSave);
    }

    @Transactional
    public DictionaryValue updateDictionaryValueName(UpdateDictionaryValueCommand command) {
        DictionaryValue dictionaryValue = getDictionaryValueById(command.getId());
        dictionaryValue.setName(command.getName().toLowerCase());
        return dictionaryValueRepository.save(dictionaryValue);
    }

    public void deleteDictionaryValueById(Long id) {
        DictionaryValue dictionaryValueToDelete = getDictionaryValueById(id);
        dictionaryValueRepository.delete(dictionaryValueToDelete);
    }

    @Transactional
    public void deleteUnassignedValues() {
        dictionaryValueRepository.deleteUnassignedValues();
    }

}