package org.task_4.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.task_4.database.model.Citizen;
import org.task_4.exceptions.NotFindException;
import org.task_4.exceptions.UniqueException;
import org.task_4.interfaces.Marker;
import org.task_4.service.CitizenService;

import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/")
public class CitizenController {
    @Autowired
    CitizenService citizenService;
    @Autowired
    Validator validator;

    @GetMapping(path = "/citizens")
    public ResponseEntity<List> getCitizensByParams(
                @RequestBody @Validated(value = {Marker.onGetting.class})
                @Valid Citizen citizen) throws NotFindException {
        var listOfCitizens = citizenService.getAllCitizensByParams(citizen);
        return new ResponseEntity<>(listOfCitizens, HttpStatusCode.valueOf(200));
    }

    @GetMapping(path = "/citizens/{id}")
    public ResponseEntity<Citizen> getCitizen(
                @PathVariable(value = "id") Long id)
                throws NotFindException {
        return new ResponseEntity<>(citizenService.getOneCitizen(id),HttpStatusCode.valueOf(200));
    }

    @PostMapping(path = "/citizens")
    public ResponseEntity<Object> addNewCitizen(
                @RequestBody @Validated(value = {Marker.onCreate.class})
                @Valid Citizen citizen) throws UniqueException {
        return new ResponseEntity<>(citizenService.saveCitizen(citizen).getId(), HttpStatus.CREATED);
    }

    @PutMapping(path = "/citizens/{id}")
    public ResponseEntity<Object> modificationDataCitizen(
                @PathVariable(value = "id") Long id,
                @RequestBody @Validated({Marker.onUpdate.class}) @Valid Citizen citizen) throws NotFindException {
        citizenService.updateCitizenByIdAndParams(id,citizen);
        return new ResponseEntity<>(id,HttpStatus.valueOf(200));
    }

    @DeleteMapping (path = "/citizens/{id}")
    public ResponseEntity<String> deleteCitizen(
                @PathVariable(value = "id") Long id) throws NotFindException {
        citizenService.deleteCitizenById(id);
        return new ResponseEntity<>("",HttpStatus.valueOf(204));
    }
}
