package cn.sysu.spring.controller;

import cn.sysu.spring.entity.Phone;
import cn.sysu.spring.entity.User;
import cn.sysu.spring.mapper.source1.UserMapper;
import cn.sysu.spring.mapper.source2.PhoneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    public UserMapper userMapper;

    @Autowired
    public PhoneMapper phoneMapper;

    @GetMapping("/user")
    public User getUser() {
        return userMapper.findByName("a");
    }

    @PostMapping("/user/add")
    public int addUser() {
        return userMapper.insert("a");
    }

    @GetMapping("/phone")
    public Phone getPhone() {
        return phoneMapper.findByPrice(10);
    }

    @PostMapping("/phone/add")
    public int addPhone() {
        return phoneMapper.insert(10);
    }
}
