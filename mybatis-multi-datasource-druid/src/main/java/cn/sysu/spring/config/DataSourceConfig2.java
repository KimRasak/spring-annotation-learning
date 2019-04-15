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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "cn.sysu.spring.mapper.source2", sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class DataSourceConfig2 {

    public static final String PREFIX_2 = "my.datasource2";
    public static final String SECONDARY_DATASOURCE = "datasource2";

    @Bean(name = SECONDARY_DATASOURCE)
    @ConfigurationProperties(prefix = PREFIX_2)
    public DataSource dataSourceAnother() {
        DruidDataSource druid = new DruidDataSource();
        // druid在该版本会在空闲时通过查询query测试连接，若不指定query语句会报错。
        druid.setTestWhileIdle(false);
        return druid;
    }

    /**
     * 创建 SqlSessionFactory
     */
    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier(SECONDARY_DATASOURCE) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务管理
     */
    @Bean(name = "db2TransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier(SECONDARY_DATASOURCE) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
