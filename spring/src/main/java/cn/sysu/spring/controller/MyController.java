package cn.sysu.spring.controller;

import cn.sysu.spring.Result.ResultBean;
import cn.sysu.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/product")
public class MyController {

    @Autowired
    public ProductService productService;

    static Lock lock = new ReentrantLock();

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/find")
    public ResultBean findProduct(@NotNull int id) {
        return ResultBean.success(productService.findById(id));
    }

    @PostMapping("/buy")
    public ResultBean buyProduct(@NotNull int id) throws InterruptedException {
        // lock.lock();
        ResultBean bean = ResultBean.success(productService.buy(id));

        // lock.unlock();
        return bean;
    }

}
