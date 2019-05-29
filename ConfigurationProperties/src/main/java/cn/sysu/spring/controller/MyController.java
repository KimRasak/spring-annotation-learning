package cn.sysu.spring.controller;


import cn.sysu.spring.entity.User;
import cn.sysu.spring.entity.UserExample;
import cn.sysu.spring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MyController {
    @Autowired
    UserMapper userMapper;

    @GetMapping
    public List<User> queryAllUser() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(1);
        return userMapper.selectByExample(userExample);
    }
}
