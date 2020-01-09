package cn.sysu.spring.service;

import cn.sysu.spring.entity.Product;
import cn.sysu.spring.mapper.ProductMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    public Product findById(int id) {
        int random = (int)((Math.random() + 0.5) * 2000);
        return productMapper.findById(id, random);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public int buy(int id) throws InterruptedException {
        Product product = findById(id);
        System.out.println("buy " + id + " " + (product.getNum() - 1));
        int res = productMapper.updateNum(id, product.getNum() - 1);
        return res;
    }
}
