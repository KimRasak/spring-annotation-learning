package cn.sysu.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Configuration // 测试@Configuration
// @Component // 测试@Component
public class InnerBeanReference {
    public static int NUMBER = 10;

    @Bean
    public SuperBean superBean() {
        return new SuperBean(innerBean());
    }

    @Bean
    public InnerBean innerBean() {
        return new InnerBean(NUMBER);
    }
}
