package cn.sysu.spring.controller;

import cn.sysu.spring.RequestBody.AddBody;
import cn.sysu.spring.RequestBody.AddPhoneBody;
import cn.sysu.spring.entity.Phone;
import cn.sysu.spring.entity.User;
import cn.sysu.spring.mapper.source1.UserMapper;
import cn.sysu.spring.mapper.source2.PhoneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public int addUser(@RequestBody AddBody body) {
        return userMapper.insert(body.name);
    }

    @GetMapping("/phone")
    public Phone getPhone() {
        return phoneMapper.findByName(10);
    }

    @PostMapping("/phone/add")
    public int addPhone(@RequestBody AddPhoneBody body) {
        return phoneMapper.insert((body.price));
    }
}
