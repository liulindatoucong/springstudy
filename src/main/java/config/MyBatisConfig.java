package config;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis配置参数
 * @author liulin
 * @date 2018年9月15日 上午11:34:34
 * @Description: TODO
 */
@Configuration
@MapperScan(basePackages = "web.mybatis.mapper")
@EnableTransactionManagement
public class MyBatisConfig {
	

	/**
	 * mysql数据源
	 * @author liulin
	 * @date 2018年9月15日 上午10:19:18
	 * @Description: TODO
	 * @return
	 */
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		bds.setUrl("jdbc:mysql://127.0.0.1:3306/tracesound?useUnicode=true&characterEncoding=UTF-8");
		bds.setUsername("root");
		bds.setPassword("liulin");
		bds.setInitialSize(5);
		bds.setMaxTotal(10);
		return bds;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception
	{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));
		return factoryBean.getObject();
	}
	
	/**
	 * 创建事物管理器
	 * @author liulin
	 * @date 2018年9月15日 上午10:28:31
	 * @Description: TODO
	 * @param dataSource
	 * @return
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager dst = new DataSourceTransactionManager();
		dst.setDataSource(dataSource);
		return dst;
	}
	
	/**
	 * 创建sqlsession
	 * @author liulin
	 * @date 2018年9月15日 上午11:15:56
	 * @Description: TODO
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean
	public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory)
	{
		SqlSessionTemplate sqlSession = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
		return sqlSession;
	}
}
