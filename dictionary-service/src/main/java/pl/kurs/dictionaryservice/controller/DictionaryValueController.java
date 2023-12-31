package pl.kurs.dictionaryservice.controller;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kurs.dictionaryservice.command.CreateDictionaryValueCommand;
import pl.kurs.dictionaryservice.command.UpdateDictionaryValueCommand;
import pl.kurs.dictionaryservice.dto.DictionaryValueDto;
import pl.kurs.dictionaryservice.dto.DictionaryValueSimpleDto;
import pl.kurs.dictionaryservice.dto.StatusDto;
import pl.kurs.dictionaryservice.model.DictionaryValue;
import pl.kurs.dictionaryservice.service.DictionaryValueService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dictionaryvalues")
@AllArgsConstructor
@Validated
public class DictionaryValueController {

    private final DictionaryValueService dictionaryValueService;

    private final ModelMapper modelMapper;

    @GetMapping
    @ApiOperation(value = "Get all dictionary values", response = DictionaryValueDto.class, responseContainer = "List")
    public ResponseEntity<List<DictionaryValueSimpleDto>> getAllDictionaryValues() {
        return ResponseEntity.ok(dictionaryValueService.getAllDictionaryValues().stream()
                .map(x -> modelMapper.map(x, DictionaryValueSimpleDto.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{dictionaryValueId}")
    @ApiOperation(value = "Get dictionary value by ID", response = DictionaryValueSimpleDto.class)
    public ResponseEntity<DictionaryValueSimpleDto> getDictionaryValueById(
            @PathVariable("dictionaryValueId") Long dictionaryValueId) {
        DictionaryValue dictionaryValue = dictionaryValueService.getDictionaryValueById(dictionaryValueId);
        DictionaryValueSimpleDto dictionaryValueDto = modelMapper.map(dictionaryValue, DictionaryValueSimpleDto.class);
        return ResponseEntity.ok(dictionaryValueDto);
    }

    @GetMapping("/{dictionaryId}/value")
    @ApiOperation(value = "Get dictionary value by dictionaryId and value name", response = DictionaryValueSimpleDto.class)
    public ResponseEntity<DictionaryValueSimpleDto> getDictionaryValueByDictionaryIdAndName(
            @PathVariable("dictionaryId") Long dictionaryId, @RequestParam String name) {
        DictionaryValue dictionaryValue = dictionaryValueService.getDictionaryValueByDictionaryIdAndName(dictionaryId, name);
        DictionaryValueSimpleDto dictionaryValueDto = modelMapper.map(dictionaryValue, DictionaryValueSimpleDto.class);
        return ResponseEntity.ok(dictionaryValueDto);
    }

    @PostMapping
    @ApiOperation(value = "Add a new dictionary value", response = DictionaryValueDto.class)
    public ResponseEntity<DictionaryValueDto> createDictionaryValue(@RequestBody @Valid CreateDictionaryValueCommand command) {
        DictionaryValue dictionaryValueForSave = dictionaryValueService.addDictionaryValue(command);
        DictionaryValueDto dictionaryValueDto = modelMapper.map(dictionaryValueForSave, DictionaryValueDto.class);
        return ResponseEntity.ok(dictionaryValueDto);
    }

    @PutMapping
    @ApiOperation(value = "Update name of dictionary value", response = DictionaryValueDto.class)
    public ResponseEntity<DictionaryValueDto> updateValueName(@RequestBody @Valid UpdateDictionaryValueCommand command) {
        DictionaryValue updatedDictionaryValue = dictionaryValueService.updateDictionaryValueName(command);
        DictionaryValueDto dictionaryValueDto = modelMapper.map(updatedDictionaryValue, DictionaryValueDto.class);
        return ResponseEntity.ok(dictionaryValueDto);
    }

    @DeleteMapping("/{dictionaryValueId}")
    @ApiOperation(value = "Delete dictionary value by ID", response = StatusDto.class)
    public ResponseEntity<StatusDto> deleteDictionaryValueById(@PathVariable("dictionaryValueId") Long dictionaryValueId) {
        dictionaryValueService.deleteDictionaryValueById(dictionaryValueId);
        return ResponseEntity.ok(new StatusDto("Dictionary value with id " + dictionaryValueId + " deleted."));
    }

    @DeleteMapping("/unassigned")
    @ApiOperation(value = "Delete unassigned dictionary values", response = StatusDto.class)
    public ResponseEntity<StatusDto> deleteUnassignedDictionaryValues() {
        dictionaryValueService.deleteUnassignedValues();
        return ResponseEntity.ok(new StatusDto("Unassigned dictionary values deleted."));
    }

}
