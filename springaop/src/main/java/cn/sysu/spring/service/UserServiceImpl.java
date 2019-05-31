package cn.sysu.spring.service;

import cn.sysu.spring.annotation.PrintMessage;
import cn.sysu.spring.entity.User;
import cn.sysu.spring.entity.UserExample;
import cn.sysu.spring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    @PrintMessage
    public List<User> queryAllUser(String str) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(1);
        System.out.println("received value: " + str);
        System.out.println("query all users.");
        return userMapper.selectByExample(userExample);
    }
}
