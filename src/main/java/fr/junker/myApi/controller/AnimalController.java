package fr.junker.myApi.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.junker.myApi.model.Animal;
import fr.junker.myApi.service.AnimalService;

@RestController
public class AnimalController {
    AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animals")
    public ResponseEntity<List<Animal>> getAnimals() throws Exception{
        List<Animal> list = animalService.getAnimals();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Controller", "getAnimals");
        if (list == null || list.size() == 0){
            headers.add("httpSatus", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<List<Animal>>(null, headers, HttpStatus.NO_CONTENT);
        }
        else{
            headers.add("httpSatus", HttpStatus.ACCEPTED.toString());
            return new ResponseEntity<List<Animal>>(list, headers, HttpStatus.ACCEPTED);
        }
        
    }

    @GetMapping("/animal/id")
    public ResponseEntity<Animal> getAnimal(@RequestParam Integer id) throws Exception{
        Animal animal = animalService.getAnimal(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Controller", "getAnimal");

        if (animal == null){
            headers.add("httpSatus", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<Animal>(null, headers, HttpStatus.NO_CONTENT);
        }
        else{
            headers.add("httpSatus", HttpStatus.ACCEPTED.toString());
            return new ResponseEntity<Animal>(animal, headers, HttpStatus.ACCEPTED);
        }
    }
        

    @PostMapping("/animal")
    public ResponseEntity<Animal> createAnimal(@RequestBody AnimalRequest animalRequest) throws Exception{
        Animal animal = animalService.createAnimal(animalRequest.getName(), animalRequest.getType(), animalRequest.getWeight());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Controller", "createAnimal");
        if (animal == null){
            headers.add("httpSatus", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<Animal>(null, headers, HttpStatus.NO_CONTENT);
        }
        else{
            headers.add("httpSatus", HttpStatus.CREATED.toString());
            return new ResponseEntity<Animal>(animal, headers, HttpStatus.CREATED);
        }
    }

    @PutMapping("/animal")
    public ResponseEntity<Animal> updateAnimal(@RequestParam Integer id, @RequestBody AnimalRequest animalRequest) throws Exception{
        Animal animal = animalService.updateAnimal(id, animalRequest.getName(), animalRequest.getType(), animalRequest.getWeight());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Controller", "updateAnimal");

        if (animal == null){
            headers.add("httpSatus", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<Animal>(null, headers, HttpStatus.NO_CONTENT);
        }
        else{
            headers.add("httpSatus", HttpStatus.ACCEPTED.toString());
            return new ResponseEntity<Animal>(animal, headers, HttpStatus.ACCEPTED);
        }
    
    }

    @DeleteMapping("/animal")
    public ResponseEntity<Animal> deleteAnimal(@RequestParam Integer id) throws Exception{
        Animal animal =  animalService.deleteAnimal(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Controller", "deleteAnimal");

        if (animal == null){
            headers.add("httpSatus", HttpStatus.NOT_FOUND.toString());
            return new ResponseEntity<Animal>(null, headers, HttpStatus.NO_CONTENT);
        }
        else{
            headers.add("httpSatus", HttpStatus.ACCEPTED.toString());
            return new ResponseEntity<Animal>(animal, headers, HttpStatus.ACCEPTED);
        }
    }

}
