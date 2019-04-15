package cn.sysu.spring.mapper.source1;

import cn.sysu.spring.entity.User;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface UserMapper extends Serializable {

    @Select("SELECT * FROM user WHERE name = #{name}")
    List<User> findByName(@Param("name") String name);

    @Insert("INSERT INTO user(name) VALUES(#{name})")
    int insert(@Param("name") String namee);

}