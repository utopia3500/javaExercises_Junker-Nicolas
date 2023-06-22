package fr.junker.myApi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.junker.myApi.model.User;
import fr.junker.myApi.service.UserService;

@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/user/id")
    public User getUser(@RequestParam Integer id){
        return userService.getUser(id);
    }

}
