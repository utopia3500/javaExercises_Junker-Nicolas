package fr.junker.myApi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.junker.myApi.dao.IUserDao;
import fr.junker.myApi.dao.JdbcUserDao;
import fr.junker.myApi.model.User;

@Service
public class UserService {
    private IUserDao dao;

    public UserService() {
        dao = new JdbcUserDao();
    }

    public User getUser(Integer id) throws Exception{
        return dao.getById(id);
    }

    public List<User> getUsers() throws Exception{
        return dao.getAll();
    }
    
    public User createUser(String name, Integer age) throws Exception{
        User user = new User(0, name, age);
        return dao.add(user);
        
    }

    public User updateUser(Integer id, String name, Integer age) throws Exception{ 
        User user = new User(id, name, age);
        dao.update(user);
        return user;

    }

    public User deleteUser(Integer id) throws Exception{
        User user = dao.getById(id);
        if (user != null){
            dao.delete(user);
            return user;
        }
        
        return null;
    }
    
}
