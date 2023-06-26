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
    public ResponseEntity<List<Animal>> getAnimals(){
        try{
            List<Animal> list = animalService.getAnimals();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Controller", "GET /animals");
            if (list == null || list.size() == 0){
                headers.add("Comment", "données non présentes en base");
                headers.add("httpSatus", HttpStatus.NOT_FOUND.toString());
                return new ResponseEntity<List<Animal>>(null, headers, HttpStatus.NOT_FOUND);
            }
            else{
                headers.add("Comment", "requete réussie");
                headers.add("httpSatus", HttpStatus.OK.toString());
                return new ResponseEntity<List<Animal>>(list, headers, HttpStatus.OK);
            }
        }
        catch(Exception ex){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Controller", "GET /animals");
            headers.add("Comment exception: ", ex.getMessage());
            headers.add("httpSatus", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            System.err.println(ex.getMessage());
            return new ResponseEntity<List<Animal>>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping("/animal/id")
    public ResponseEntity<Animal> getAnimal(@RequestParam Integer id){
        try{
            Animal animal = animalService.getAnimal(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Controller", "GET /animal/id");

            if (animal == null){
                headers.add("httpSatus", HttpStatus.NOT_FOUND.toString());
                headers.add("Comment", "donnée non trouvée en base");
                return new ResponseEntity<Animal>(null, headers, HttpStatus.NOT_FOUND);
            }
            else{
                headers.add("Comment", "requete réussie");
                headers.add("httpSatus", HttpStatus.OK.toString());
                return new ResponseEntity<Animal>(animal, headers, HttpStatus.OK);
            }
        }   
        catch(Exception ex){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Controller", "GET /animal/id");
            headers.add("Comment exception: ", ex.getMessage());
            headers.add("httpSatus", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            System.err.println(ex.getMessage());
            return new ResponseEntity<Animal>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        

    @PostMapping("/animal")
    public ResponseEntity<Animal> createAnimal(@RequestBody AnimalRequest animalRequest){
        try{
            Animal animal = animalService.createAnimal(animalRequest.getName(), animalRequest.getType(), animalRequest.getWeight());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Controller", "POST /animal");
            if (animal == null){
                headers.add("httpSatus", HttpStatus.NOT_FOUND.toString());
                headers.add("Comment", "erreur lors de la création en base");
                return new ResponseEntity<Animal>(null, headers, HttpStatus.NOT_FOUND);
            }
            else{
                headers.add("httpSatus", HttpStatus.CREATED.toString());
                headers.add("Comment", "requete réussie");
                return new ResponseEntity<Animal>(animal, headers, HttpStatus.CREATED);
            }
        }
        catch(Exception ex){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Controller", "POST /animal");
            headers.add("Comment exception: ", ex.getMessage());
            headers.add("httpSatus", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            System.err.println(ex.getMessage());
            return new ResponseEntity<Animal>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/animal")
    public ResponseEntity<Animal> updateAnimal(@RequestParam Integer id, @RequestBody AnimalRequest animalRequest){
        try{
            Animal animal = animalService.updateAnimal(id, animalRequest.getName(), animalRequest.getType(), animalRequest.getWeight());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Controller", "PUT /animal");

            if (animal == null){
                headers.add("httpSatus", HttpStatus.NOT_FOUND.toString());
                headers.add("Comment", "donnée non trouvée en base");
                return new ResponseEntity<Animal>(null, headers, HttpStatus.NOT_FOUND);
            }
            else{
                headers.add("httpSatus", HttpStatus.OK.toString());
                headers.add("Comment", "requete réussie");
                return new ResponseEntity<Animal>(animal, headers, HttpStatus.OK);
            }
        }catch(Exception ex){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Controller", "PUT /animal");
            headers.add("Comment exception: ", ex.getMessage());
            headers.add("httpSatus", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            System.err.println(ex.getMessage());
            return new ResponseEntity<Animal>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }

    @DeleteMapping("/animal")
    public ResponseEntity<Animal> deleteAnimal(@RequestParam Integer id){
        try{
            Animal animal =  animalService.deleteAnimal(id);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Controller", "DELETE /animal");

            if (animal == null){
                headers.add("httpSatus", HttpStatus.NOT_FOUND.toString());
                headers.add("Comment", "donnée non trouvée en base");
                return new ResponseEntity<Animal>(null, headers, HttpStatus.NOT_FOUND);
            }
            else{
                headers.add("httpSatus", HttpStatus.OK.toString());
                headers.add("Comment", "requete réussie");
                return new ResponseEntity<Animal>(animal, headers, HttpStatus.OK);
            }
        }
        catch(Exception ex){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Controller", "DELETE /animal");
            headers.add("httpSatus", HttpStatus.INTERNAL_SERVER_ERROR.toString());
            headers.add("Comment exception: ", ex.getMessage());
            System.err.println(ex.getMessage());
            return new ResponseEntity<Animal>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
