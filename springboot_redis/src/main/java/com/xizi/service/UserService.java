package com.xizi.service;

import com.xizi.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    List<User> findAll();

    User findById(String id);

    void delete(String id);

    void save(User user);

    void update(User user);
}
