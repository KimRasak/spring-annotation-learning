package cn.sysu.spring.mapper;

import cn.sysu.spring.entity.Product;
import org.apache.ibatis.annotations.*;

public interface ProductMapper {

    @Select("SELECT * FROM product WHERE id = #{id} and num <= #{randomNum}")
    Product findById(@Param("id") int id, int randomNum);

    @Insert("UPDATE product set num = num -  1 where id = #{id}")
    int decreaseByOne(@Param("id") int id);

    @Update("UPDATE product set num = #{num} where id = #{id}")
    int updateNum(@Param("id") int id, @Param("num") int num);
}
