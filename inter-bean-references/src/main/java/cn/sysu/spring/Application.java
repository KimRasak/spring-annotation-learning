package cn.sysu.spring;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired private SuperBean superBean;
	@Autowired private InnerBean innerBean;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
	}

	/**
	 * 用context.getBean注入Bean，来测试Bean的级联问题
	 * @param context 应用上下文
	 */
	public static void testSuperBeanWithContext(ApplicationContext context) {
		SuperBean superBean = context.getBean(SuperBean.class);
		superBean.innerBean.number = 12;

		InnerBean innerBean = context.getBean(InnerBean.class);
		System.out.println(innerBean.number);
	}

	/**
	 * 用@Autowired注入Bean，来测试Bean的级联问题
	 */
	public void testSuperBeanWithAutowired() {
		superBean.innerBean.number = 12;
		System.out.println(innerBean.number);
	}

	@Override
	public void run(String... args) throws Exception {
		testSuperBeanWithAutowired();
	}
}
