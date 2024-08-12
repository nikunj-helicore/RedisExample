package com.api.redis.dao;

import com.api.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {
    private final RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public UserDao(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    private static final String KEY="user";

    public User save(User user) {
        redisTemplate.opsForHash().put(KEY,user.getId(),user);
        return user;
    }
    public User getUser(String id) {
        return (User) redisTemplate.opsForHash().get(KEY,id);
    }
    public void deleteUser(String id) {
        redisTemplate.opsForHash().delete(KEY,id);
    }
    public Map<Object,Object> getAllUser() {
        return redisTemplate.opsForHash().entries(KEY);
    }
}
