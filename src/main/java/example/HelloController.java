package example;

import example.model.Dog;
import example.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {

    @Autowired
    private DogRepository dogRepository;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/createdog")
    public String createDog(@RequestParam String name) {
        Dog dog = new Dog();
        dog.setName(name);
        dog.setTailWaggedTimes(999);
        dog.setGoodDog(Boolean.TRUE);
        dogRepository.saveAndFlush(dog);
        return "Just created a dog!";
    }

    @RequestMapping("/renamedog")
    public String renameDog(@RequestParam int id, @RequestParam String name) {
        Optional<Dog> dog = dogRepository.findById(id);
        if (dog.isPresent()) {
            dog.get().setName(name);
            dogRepository.saveAndFlush(dog.get());
            return "Updated a dog!";
        }
        return "No dog updated!";
    }

    @RequestMapping("/deletedog")
    public String deleteDog(@RequestParam int id) {
        Optional<Dog> dog = dogRepository.findById(id);
        if (dog.isPresent()) {
            dogRepository.delete(dog.get());
            return "Deleted a dog!";
        }
        return "No dog deleted!";
    }

    @RequestMapping("/listdogs")
    public String listDogs() {
        List<Dog> dogs = dogRepository.findAll();
        final StringBuffer dogList = new StringBuffer();

        dogs.forEach((dog) -> {
            dogList.append(dog.getName() + ", ");
        });

//        for (Dog dog : dogs) {
//            dogList = dogList + dog.getName() + ", ";
//        }

//        for (int i = 0; i < dogs.size(); i++) {
//            Dog dog = dogs.get(i);
//            dogList = dogList + dog.getName() + ", ";
//        }
        return dogList.toString();
    }
}