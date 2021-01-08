package com.xizi.dao;

import com.xizi.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserDao {

    List<User> findAll();

    User findById(String id);

    void delete(String id);

    void save(User user);

    void update(User user);
}
