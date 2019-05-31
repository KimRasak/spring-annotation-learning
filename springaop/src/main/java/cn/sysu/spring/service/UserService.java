package cn.sysu.spring.service;

import cn.sysu.spring.entity.User;

import java.util.List;

public interface UserService {
    public List<User> queryAllUser(String str);
}
