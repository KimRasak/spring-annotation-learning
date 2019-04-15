package cn.sysu.spring.mapper.source1;

import cn.sysu.spring.entity.User;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends Serializable {

    @Select("SELECT * FROM user WHERE name = #{name}")
    User findByName(@Param("name") String name);

    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT name, age FROM user")
    List<User> findAll();

    @Insert("INSERT INTO user(name) VALUES(#{name})")
    int insert(@Param("name") String namee);

}