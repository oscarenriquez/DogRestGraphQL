package com.udacity.jwdnd.dogapi.mutator;

import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.jwdnd.dogapi.entity.Dog;
import com.udacity.jwdnd.dogapi.exception.BreedNotFoundException;
import com.udacity.jwdnd.dogapi.exception.DogNotFoundException;
import com.udacity.jwdnd.dogapi.repository.DogRepository;

import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog createDog(String name, String breed, String origin) {
        Dog dog = new Dog(name, breed, origin);
        return this.dogRepository.save(dog);
    }

    public boolean deleteDogBreed(String breed) {
        boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        // Loop through all dogs to check their breed
        for (Dog d:allDogs) {
            if (d.getBreed().equals(breed)) {
                // Delete if the breed is found
                dogRepository.delete(d);
                deleted = true;
            }
        }
        // Throw an exception if the breed doesn't exist
        if (!deleted) {
            throw new BreedNotFoundException("Breed Not Found", breed);
        }
        return deleted;
    }

    public Dog updateDogName(String newName, Long id) {
        Optional<Dog> optionalDog = this.dogRepository.findById(id);

        if(optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            dog.setName(newName);

            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }

}
