package cn.sysu.spring.service;

import cn.sysu.spring.annotation.ChangeValue;
import cn.sysu.spring.entity.User;

import java.util.List;

public interface IService {

    public List<User> queryAllUser(String str);
}
