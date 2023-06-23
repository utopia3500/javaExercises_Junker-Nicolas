package fr.junker.myApi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.junker.myApi.dao.IAnimalDao;
import fr.junker.myApi.dao.JdbcAnimalDao;
import fr.junker.myApi.model.Animal;

@Service
public class AnimalService {
    private IAnimalDao dao;

    public AnimalService() {
        dao = new JdbcAnimalDao();
    }

    public Animal getAnimal(Integer id) throws Exception{
        return dao.getById(id);
    }

    public List<Animal> getAnimals() throws Exception{
        return dao.getAll();
    }
    
    public Animal createAnimal(String name, String type, Integer weight) throws Exception{
        Animal animal = new Animal(0, name, type, weight);
        return dao.add(animal);
        
    }

    public Animal updateAnimal(Integer id, String name, String type, Integer weight) throws Exception{ 
        Animal animal = new Animal(id, name, type, weight);
        dao.update(animal);
        return animal;

    }

    public Animal deleteAnimal(Integer id) throws Exception{
        Animal animal = dao.getById(id);
        if (animal != null){
            dao.delete(animal);
            return animal;
        }
        
        return null;
    }
}
