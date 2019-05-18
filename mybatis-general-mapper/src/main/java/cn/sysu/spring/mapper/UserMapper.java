package cn.sysu.spring.mapper;

import cn.sysu.spring.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    List<User> queryAllUser();
}

