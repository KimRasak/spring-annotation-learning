package cn.sysu.spring.mapper.source2;

import cn.sysu.spring.entity.Phone;
import cn.sysu.spring.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PhoneMapper {
    @Select("SELECT * FROM phone WHERE price = #{price}")
    Phone findByName(@Param("price") int price);


    @Insert("INSERT INTO phone(price) VALUES(#{price})")
    int insert(@Param("price") int price);
}
