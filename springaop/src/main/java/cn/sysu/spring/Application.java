package cn.sysu.spring;

import cn.sysu.spring.annotation.ChangeValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/***
 * 使用EnableConfigurationProperties引入MyConfig
 * MyComponent已使用@Component注册为组件，直接用@Autowired引入即可
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.sysu.spring.mapper")
public class Application {

	interface Person {
		public void say();
	}

	static class Student implements Person {
		@Override
		public void say() {
			System.out.println("student say");
		}

		public void walk() {
			System.out.println("student walk");
		}
	}

	static class Handler implements InvocationHandler {
		private Student s;

		public Handler(Student s) {
			this.s = s;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("before");
			return method.invoke(s, args);
		}
	}

	public static void main(String[] args) {
		System.out.println(B.foo);
		// ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
	}

	public static void test() {
		Handler h = new Handler(new Student());

		Person s = (Person) Proxy.newProxyInstance(Student.class.getClassLoader(), Student.class.getInterfaces(), h);
		s.say();
	}

}
