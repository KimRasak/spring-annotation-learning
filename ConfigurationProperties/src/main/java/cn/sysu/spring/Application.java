package cn.sysu.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/***
 * 使用EnableConfigurationProperties引入MyConfig
 * MyComponent已使用@Component注册为组件，直接用@Autowired引入即可
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.sysu.spring.mapper")
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		testStudentBean(context);
	}

	private static void testStudentBean(ApplicationContext context) {
		StudentBean studentBean = context.getBean(StudentBean.class);
		System.out.println(studentBean.getName());
	}
}
