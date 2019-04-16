package cn.sysu.spring.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 6, max = 20, message = "用户名需要为 6 - 20 个字符")
    private String name;

    public User() {
    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
