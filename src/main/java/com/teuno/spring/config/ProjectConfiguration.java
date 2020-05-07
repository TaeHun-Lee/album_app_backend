package com.teuno.spring.config;

import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@MapperScan("com.teuno.spring.mappers")
public class ProjectConfiguration {
	
	@Autowired
	private ApplicationContext appCtx;
	
	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_test?characterEncoding=utf8&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("dogbook7!");
		return dataSource;
	}
	
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
      SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
      factoryBean.setDataSource(dataSource());
      factoryBean.setConfigLocation(appCtx.getResource("classpath:mybatis/config/mybatis-config.xml"));
      factoryBean.setMapperLocations(appCtx.getResources("classpath:mybatis/mapper/*.xml"));
      return factoryBean.getObject();
    }
    
    @Bean
    public SqlSession sqlSession() throws Exception {
      SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
      return sqlSessionTemplate;
    }
    
	@Bean
	public MultipartResolver multipartResolver() {
	  CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	  multipartResolver.setMaxUploadSize(52428800); // 10MB
      multipartResolver.setMaxUploadSizePerFile(52428800); // 10MB
      multipartResolver.setMaxInMemorySize(52428800);
      multipartResolver.setDefaultEncoding("UTF-8");
	  return multipartResolver;
	}
}
