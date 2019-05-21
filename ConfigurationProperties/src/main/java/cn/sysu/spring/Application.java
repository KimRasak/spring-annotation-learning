package cn.sysu.spring;

import cn.sysu.spring.entity.Big;
import cn.sysu.spring.mapper.BigMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/***
 * 使用EnableConfigurationProperties引入MyConfig
 * MyComponent已使用@Component注册为组件，直接用@Autowired引入即可
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.sysu.spring.mapper")
public class Application {

	@Autowired
    BigMapper bigMapper;


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		testStudentBean(context);
	}

	private static void testStudentBean(ApplicationContext context) {
		StudentBean studentBean = context.getBean(StudentBean.class);
		System.out.println(studentBean.getName());
	}

	private void insertRows() {
		for (int i = 0; i < 1000000; i++) {
			Big line = new Big();
			line.setNumber(2);
			bigMapper.insertSelective(line);
			if (i % 1000 == 0)
				System.out.println("insert " + i + " line.");
		}
	}
}
