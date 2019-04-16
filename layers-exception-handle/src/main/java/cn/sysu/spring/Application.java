package cn.sysu.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// StdErrLog.tieSystemOutAndErrToLog();
		SpringApplication.run(Application.class, args);
	}

}
