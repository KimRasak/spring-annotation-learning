package org.spring.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 城市业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class CityServiceImpl implements CityService {

    // 模拟数据库存储
    private Map<String, City> cityMap = new HashMap<String, City>();

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void saveCity(City city){
        // 模拟数据库插入操作
        cityMap.put(city.getCityName(), city);
    }

    @Override
    @Cacheable(value = "cityInfo", key = "#cityName")
    public City getCityByName(String cityName){
        // 模拟数据库查询并返回
        return cityMap.get(cityName);
    }

    @Override
    @CachePut(value = "cityInfo", key = "#cityName")
    public City updateCityDescription(String cityName, String description){
        City city = cityMap.get(cityName);
        city.setDescription(description);
        // 模拟更新数据库
        cityMap.put(cityName, city);

        return city;
    }

}
