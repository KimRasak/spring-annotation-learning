package cn.sysu.spring.service;

import cn.sysu.spring.entity.User;
import cn.sysu.spring.exception.InvalidNameException;
import cn.sysu.spring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> findByName(String a) throws InvalidNameException {
        if (a.equals("invalidName"))
            throw new InvalidNameException();
        return userMapper.findByName(a);
    }

    public int insert(String a) {
        return userMapper.insert(a);
    }
}
