package org.spring.springboot.devTools;

import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.welab.common.config.DefaultConfigService;
import com.welab.xdao.generator.Generator;
import com.welab.xdao.util.XDaoUtil;

/**
 * 持久化构建,生成代码
 */
public class AutoGenCode {
	public static void main(String[] args) {
		//对数据库表的任何修改，执行该方法，重新生成持久化

		//加载配置
		DefaultConfigService config = new DefaultConfigService("springboot-mybatis-redis", "server.properties");
		config.init();

		//数据库配置
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(config.getProperty("jdbc.url"));
		dataSource.setUsername(config.getProperty("jdbc.username"));
		dataSource.setPassword(config.getProperty("jdbc.password"));
		dataSource.setInitialSize(1);
		dataSource.setMinIdle(1);
		dataSource.setMaxActive(20);
		dataSource.setMaxWait(60000);
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setValidationQuery("SELECT 'x'");
		try {
			dataSource.setFilters(config.getProperty("druid.filters"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		dataSource.setConnectionProperties(config.getProperty("druid.connectionProperties"));
		XDaoUtil.DATASOURCE = dataSource;

		//配置表的持久化**"DataTrail"**功能（数据的增删改留痕）
//		DBUtil.putDataTrails("ca_account_info", "user_info_traces");

		//生成表的包名配置
		String domain = config.getProperty("table.package");
		//生成所有表，参数2为输出目录，填null则输出在本项目的src/main/java目录下
		Generator.execute(domain, null, "lock_version");
	}
}
