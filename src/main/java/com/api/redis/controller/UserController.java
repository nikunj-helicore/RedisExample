package com.api.redis.controller;

import com.api.redis.dao.UserDao;
import com.api.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    public final UserDao userDao;
    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping
    public User createUSer(@RequestBody User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        return userDao.save(user);
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return userDao.getUser(userId);
    }

    @GetMapping
    public List<User> getUsers() {
        Map<Object,Object> all =  userDao.getAllUser();
        return all.values().stream().map(value-> (User)value).toList();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        userDao.deleteUser(userId);
    }
}
