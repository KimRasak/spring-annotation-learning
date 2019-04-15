package cn.sysu.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "cn.sysu.spring.mapper.source1", sqlSessionTemplateRef = "db1SqlSessionTemplate")
public class DataSourceConfig {

    public static final String PREFIX_1 = "spring.datasource";
    public static final String PRIMARY_DATASOURCE = "datasource1";

    @Bean(name = PRIMARY_DATASOURCE)
    @ConfigurationProperties(prefix = PREFIX_1)
    @Primary
    public DataSource dataSourceOne() {
        DruidDataSource druid = new DruidDataSource();
        // druid在该版本会在空闲时通过查询query测试连接，若不指定query语句会报错。
        druid.setTestWhileIdle(false);
        return druid;
    }

    /**
     * 创建 SqlSessionFactory
     */
    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier(PRIMARY_DATASOURCE) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务管理
     */
    @Bean(name = "db1TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier(PRIMARY_DATASOURCE) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "db1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
