package cn.sysu.spring;

import cn.sysu.spring.entity.Big;
import cn.sysu.spring.entity.BigExample;
import cn.sysu.spring.mapper.BigMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.sysu.spring.mapper")
public class Application implements CommandLineRunner {

	@Autowired
	BigMapper bigMapper;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// insertRows

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
