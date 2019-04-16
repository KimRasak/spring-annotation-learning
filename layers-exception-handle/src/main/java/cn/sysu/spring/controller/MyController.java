package cn.sysu.spring.controller;

import cn.sysu.spring.Result.ResultBean;
import cn.sysu.spring.entity.User;
import cn.sysu.spring.exception.InvalidNameException;
import cn.sysu.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class MyController {

    @Autowired
    public UserService userService;

    // 非法的用户名会导致 抛出异常
    @GetMapping("/user")
    public ResultBean getUser(@NotNull String name) throws InvalidNameException {
        return ResultBean.success(userService.findByName(name));
    }

    // 格式错误的User参数会导致抛出异常
    @PostMapping("/user/add")
    public ResultBean addUser(@Valid User user) {
        return ResultBean.success();
    }

    // 故意进行错误运算
    @GetMapping("/math")
    public int divide() { return 1 / 0;}
}
