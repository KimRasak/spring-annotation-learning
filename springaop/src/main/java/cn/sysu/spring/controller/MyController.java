package cn.sysu.spring.controller;


import cn.sysu.spring.annotation.ChangeValue;
import cn.sysu.spring.annotation.PrintMessage;
import cn.sysu.spring.entity.User;
import cn.sysu.spring.service.UserServiceImpl;
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
    UserServiceImpl userServiceImpl;

    @GetMapping
    @Transactional
    public List<User> queryAllUser() {
       return userServiceImpl.queryAllUser("hello");
    }


    @ChangeValue
    @PrintMessage
    private String say(String str) {
        System.out.println(str);
        return str + "a";
    }

}
