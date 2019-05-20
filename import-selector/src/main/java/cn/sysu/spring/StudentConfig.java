package cn.sysu.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    public StudentBean getStudent() {
        return new StudentBean("jzl");
    }
}
