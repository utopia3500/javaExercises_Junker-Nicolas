package fr.junker.myApi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<User> getUsers() throws Exception{
        return userService.getUsers();
    }

    @GetMapping("/user/id")
    public User getUser(@RequestParam Integer id) throws Exception{
        return userService.getUser(id);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody UserRequest userRequest) throws Exception{
        User result = userService.createUser(userRequest.getName(), userRequest.getAge());
        return result;
    }

    @PutMapping("/user")
    public User updateUser(@RequestParam Integer id, @RequestBody UserRequest userRequest) throws Exception{
        return userService.updateUser(id, userRequest.getName(), userRequest.getAge());
    }

    @DeleteMapping("/user")
    public User deleteUser(@RequestParam Integer id) throws Exception{
        return userService.deleteUser(id);
    }

}
