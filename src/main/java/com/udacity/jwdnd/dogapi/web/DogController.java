package com.udacity.jwdnd.dogapi.web;

import com.udacity.jwdnd.dogapi.service.DogService;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogController {

    private DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/dog")
    public ResponseEntity<List<String>> getAllDogs() {
        return new ResponseEntity<List<String>>(this.dogService.retrieveDogNames(), HttpStatus.OK);
    }
}
