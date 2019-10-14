package cn.sysu.spring;

import cn.sysu.spring.controller.MyController;
import cn.sysu.spring.entity.Product;
import cn.sysu.spring.mapper.ProductMapper;
import cn.sysu.spring.service.ProductService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private SqlSessionFactory factory;

	@Autowired
	private ProductService productService;

	@Autowired
	private MyController myController;

	@Autowired
	private ProductMapper productMapper;

	private final int TEST_NUM = 100;

	private final int THREAD_NUM = 2;


	@Test
	public void showDefaultCacheConfiguration() {
		System.out.println("一级缓存范围: " + factory.getConfiguration().getLocalCacheScope());
		System.out.println("二级缓存是否被启用: " + factory.getConfiguration().isCacheEnabled());
	}

	@Test
	public void testSelect() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(THREAD_NUM);
		Thread []ts = new Thread[THREAD_NUM];
		for (int i = 0; i < THREAD_NUM; i++) {
			ts[i] = new Thread(() -> {
				try {
					myController.buyProduct(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				latch.countDown();
			});
			ts[i].start();
		}
		latch.await();
	}

	// @Test
	public void contextLoads() throws InterruptedException {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(30);
		CountDownLatch latch = new CountDownLatch(TEST_NUM);
		for (int i = 0; i < TEST_NUM; i++) {
			executor.submit(() -> {
				try {
					productService.buy(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				latch.countDown();
			});
		};
		latch.await();
		executor.shutdown();
	}

}
