package com.udacity.jwdnd.dogapi.service.impl;

import com.udacity.jwdnd.dogapi.entity.Dog;
import com.udacity.jwdnd.dogapi.repository.DogRepository;
import com.udacity.jwdnd.dogapi.service.DogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogServiceImpl implements DogService {

    private DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public List<Dog> retrieveDogs() {
        return (List<Dog>) dogRepository.findAll();
    }

    public List<String> retrieveDogBreed() {
        return (List<String>) dogRepository.findAllBreed();
    }

    public String retrieveDogBreedById(Long id) {
        return (String) dogRepository.findBreedById(id);
    }

    public List<String> retrieveDogNames() {
        return (List<String>) dogRepository.findAllName();
    }

}
