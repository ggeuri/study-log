package com.ch.shop.config.spring;



import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


// 로직 위한게 아니라 애플리케이션 Bean(객체)들 및 관계 명시하기위한 설정 목적 클래스. 쇼핑몰의 일반 유저들이 보게되는 애플리케이션쪽 빈들 관리 
@Configuration 
@EnableWebMvc //필수설정(스프링이 지원하는 MVC프레임워크 사용위한 어노테이션) 

//일일이 빈으로 등록할 필요가 없는 많이 알려진 Bean(Controller, Service, Repository, Component ) 들을 가리켜 컴포넌트라함. 이 컴포넌트들은패키지위치만 설정해놓으면 스프링이 알아서 찾아내서 인스턴스 자동으로 만들어줌 
@ComponentScan(basePackages = "com.ch.shop.controller")
public class UserWebConfig {
	
	//DispatcherServlet이 하위 컨트롤러로 부터 반환받은 결과 페이지에 대한 정보는 사실 완전한 JSP경로가 아니므로,
	//이를 해석할 수 있는 자인 ViewResolver에게 맡겨야 하는데, 이 ViewResolver 중 유달리 접두어와 접미어 방식을 이해하는
	//뷰리졸버를 InternalResourceViewResolver라고 한다..개발자는 이 객체에게 접두어와 접미어를 사전에 등록해 놓아야 한다
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver rv = new InternalResourceViewResolver();
		//접두어 등록 
		rv.setPrefix("/WEB-INF/views/");
		//접미어
		rv.setSuffix(".jsp");
		
		return rv ; 
	}
	
	/*
	 스프링이 MVC 프레임워크 중 컨트롤러 영역만을 지원하는 것이 아니라, 데이터베이스 관련 제어도 지원하므로,
	 지금까지 순수하게 사용해왔던 mybatis를 스프링이 지원하는 mybatis로 전환해본다
	 스프링이 지원하는 데이터 연동 기술을 사용하려면, spring jdbc 라이브러리를 추가해야 한다..
	 spring jdbc
	*/

	/*---------------------------------------------------------
	1) 개발자가 사용하고 싶은데 데이터소스를 결정
	 - 톰켓이 지원하는 JNDI를 사용할 예정
	---------------------------------------------------------*/
	
	public DataSource dataSource() throws NamingException{
		JndiTemplate jndi = new JndiTemplate();
		return jndi.lookup("java:comp/env/jndi/mysql",DataSource.class); 
	}
	
	/*---------------------------------------------------------
	2) 트랜잭션 매니저 등록
	- 스프링은 개발자가 사용하는 기술이 JDBC, Mybatis, Hibernate, JPA 이건 상관없이
	  일관된 방법으로 트랜잭션을 처리할 수있는 방법을 제공해주는데, 개발자는 자신이 사용하는 기술에 따라
	  적절할 트랜잭션 매니저를 등록해야 한다
	예) JDBC 사용 시 - DataSourceTransactionManager를 빈으로 등록해야 함
	예) Hibernate 사용 시 - HibernateTransactionManager 를 빈으로 등록해야 함
	예) Mybatis 사용 시 - DataSourceTransactionManager를 빈으로 등록해야 함
	      특히 Mybatis 의 경우 JDBC와 동일한 DataSourceTransactionManager를 사용하는 이유는?
	      사실 Mybatis는 내부적으로, JDBC를 사용하기 때문임..
	      그리고 이 모든 트랜잭션 매니저의 최상단 객체가 바로 PlatformTransactionManager 
	      	---------------------------------------------------------*/
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		
		return new DataSourceTransactionManager() ; 
	}
	
	/*---------------------------------------------------------
	3) SqlSession을 관리하는 mybatis의 SqlSessionFactory를 빈으로 등록
	---------------------------------------------------------*/

	@Bean
	public SqlSessionFactory sqlSessionFactory() {
		//순수 Mybatis 프레임워크 자체에서 지원하는 객체가 아니라 mybatis-spring에서 지원하는 객체 SqlSessionFactoryBean 이용하여 설정xml파일 로드 
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("com/ch/shop/config/mybatis/config.xml"));
		return null;
	}
	

}
