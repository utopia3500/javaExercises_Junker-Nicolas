package fr.junker.myApi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.junker.myApi.model.User;

@Service
public class UserService {
    private List<User> users;

    public UserService() {
        users = new ArrayList<User>();

        User user1 = new User(1, "Dupond", 50);
        User user2 = new User(2, "Durand", 60);
        User user3 = new User(3, "Martin", 35);

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    public User getUser(Integer id){
        User result = null;

        for (User user : this.users){
            if (user.getId() == id){
                return user;               
            }
        }

        return result;
    }

    public List<User> getUsers(){
        return users;
    }

    
}
