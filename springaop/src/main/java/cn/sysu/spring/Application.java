package cn.sysu.spring;

import cn.sysu.spring.annotation.ChangeValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/***
 * 使用EnableConfigurationProperties引入MyConfig
 * MyComponent已使用@Component注册为组件，直接用@Autowired引入即可
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.sysu.spring.mapper")
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
	}

}
