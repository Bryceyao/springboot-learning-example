package org.spring.springboot.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = RelaDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "relaSqlSessionFactory")
public class RelaDataSourceConfig {

    // 精确到 rela 目录，以便跟其他数据源隔离
    static final String PACKAGE = "org.spring.springboot.dao.rela";
    static final String MAPPER_LOCATION = "classpath:mapper/rela/*.xml";

    @Value("${rela.datasource.url}")
    private String url;

    @Value("${rela.datasource.username}")
    private String user;

    @Value("${rela.datasource.password}")
    private String password;

    @Value("${rela.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "relaDataSource")
    public DataSource relaDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "relaTransactionManager")
    public DataSourceTransactionManager relaTransactionManager() {
        return new DataSourceTransactionManager(relaDataSource());
    }

    @Bean(name = "relaSqlSessionFactory")
    public SqlSessionFactory relaSqlSessionFactory(@Qualifier("relaDataSource") DataSource relaDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(relaDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(RelaDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}