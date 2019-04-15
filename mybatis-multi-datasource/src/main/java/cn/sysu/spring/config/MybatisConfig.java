package cn.sysu.spring.config;

import cn.sysu.spring.mapper.source1.UserMapper;
import cn.sysu.spring.mapper.source2.PhoneMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class MybatisConfig {
//    private static final String ONE_SESSION_FACTORY = "oneSessionFactory";
//    private static final String ANOTHER_SESSION_FACTORY = "anotherSessionFactory";
//
//    @Bean(name = ONE_SESSION_FACTORY, destroyMethod = "")
//    @Primary
//    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier(DatabaseConfig.PRIMARY_DATASOURCE) final DataSource oneDataSource) throws Exception {
//        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(oneDataSource);
//        SqlSessionFactory sqlSessionFactory;
//        sqlSessionFactory = sqlSessionFactoryBean.getObject();
//        sqlSessionFactory.getConfiguration().addMappers(UserMapper.class.getPackage().getName());
//        // Various other SqlSessionFactory settings
//        return sqlSessionFactoryBean;
//    }
//
//    @Bean
//    public MapperFactoryBean<UserMapper> etrMapper(@Qualifier(ONE_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
//        MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<>(UserMapper.class);
//        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
//        return factoryBean;
//    }
//
//    @Bean(name = ANOTHER_SESSION_FACTORY, destroyMethod = "")
//    public SqlSessionFactoryBean censoSqlSessionFactory(@Qualifier(DatabaseConfig.SECONDARY_DATASOURCE) final DataSource anotherDataSource) throws Exception {
//        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(anotherDataSource);
//        final SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
//        sqlSessionFactory.getConfiguration().addMapper(PhoneMapper.class);
//        // Various other SqlSessionFactory settings
//        return sqlSessionFactoryBean;
//    }
//
//    @Bean
//    public MapperFactoryBean<PhoneMapper> dbMapper(@Qualifier(ANOTHER_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
//        MapperFactoryBean<PhoneMapper> factoryBean = new MapperFactoryBean<>(PhoneMapper.class);
//        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
//        return factoryBean;
//    }
}