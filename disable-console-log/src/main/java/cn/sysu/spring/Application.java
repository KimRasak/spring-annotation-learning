package cn.sysu.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	private static Log log = LogFactory.getLog(Application.class);
	public static void main(String[] args) {
		log.info("writen in file.");
		SpringApplication.run(Application.class, args);
	}

	@GetMapping
	private String hello() { return "hello."; }

}
