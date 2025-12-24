package com.ch.shop.config.spring;

import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ch.shop.util.FileManager;

/*
 [RootConfig 역할]
 - 전통적인 spring xml(빈 등록 파일)을 "자바 설정 클래스"로 대체한 것
 - 특히 여기 등록되는 빈들은 "모델/비즈니스/DB 인프라"에 해당하므로,
   특정 DispatcherServlet(서블릿) 전용 컨텍스트가 아니라,
   모든 서블릿이 공유하는 "Root Spring Context(전역 컨텍스트)"에서 관리되어야 한다.

 [왜 전역(Root)에서 관리해야 하나?]
 - 서비스/DAO/트랜잭션/DB 연결 같은 모델 영역은
   admin/shop 어떤 요청이 와도 공통으로 필요
 - 그래서 서블릿 단위가 아니라 ServletContext(애플리케이션 전역) 수준에서 생성/공유되는
   Root 컨테이너가 이 설정을 읽고 빈을 만들어 관리하는 구조가 된다.
*/
@Configuration
@ComponentScan(basePackages = {"com.ch.shop.model","com.ch.shop.util" })
@EnableTransactionManagement
public class RootConfig extends WebMvcConfigurerAdapter {
	// 스프링프레임웍을 지배하는 개발원리 중 하나인 DI를 구현하려면 개발자는 사용할 객체들을 미리 빈으로 등록해야 한다..
	// DispatcherServlet이 하위 컨트롤러로 부터 반환받은 결과 페이지에 대한 정보는 사실 완전한 JSP경로가 아니므로,
	// 이를 해석할 수 있는 자인 ViewResolver에게 맡겨야 하는데, 이 ViewResolver 중 유달리 접두어와 접미어 방식을 이해하는
	// 뷰리절버를 InternalResourceViewResolver라고 한다..개발자는 이 객체에게 접두어와 접미어를 사전에 등록해 놓아야 한다


	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver rv = new InternalResourceViewResolver();
		// /WEB-INF/views/ board/list .jsp
		rv.setPrefix("/WEB-INF/views/");// 접두어 등록

		rv.setSuffix(".jsp");// 접미어 등록
		return rv;
	}
	// DispatcherServlet은 컨트롤러에 대한 매핑만 수행하면 되며, 정적자원(css, js, html, imgage 등)에 대해서는
	// 직접 처리하지
	// 않게 하기
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// registry.addResourceHandler("브라우저로 접근할 주소").addResourceLocations("웹애플리케이션을
		// 기준으로 실제 정적자원이 잇는 우치")
		registry.addResourceHandler("/static/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/photo/**").addResourceLocations( "file:/Users/rimu/shopdata/product/");
	}

	// Jackson 라이브러리 사용 위한 설정
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter()); // 잭슨넣을거임
	}
	

	/*
	 [큰 흐름(목표)]
	 - "순수 MyBatis"만 쓰던 것을, 스프링이 지원하는 mybatis-spring 방식으로 전환
	 - 장점: 스프링 DI/트랜잭션 관리와 자연스럽게 결합됨
	 - (참고) 스프링의 DB 연동 기능을 쓰려면 spring-jdbc 같은 라이브러리도 필요할 수 있음
	 */

	/*----------------------------------------------------
	 1) DataSource 결정/등록 (DB 연결 자원)
	    - 톰캣이 제공하는 JNDI를 이용해 DataSource를 얻어온다
	    - 즉, DB 접속 정보(host, id/pw 등)를 코드에 박지 않고 서버 설정(JNDI)에 맡기는 방식
	    - 결과: 다른 환경(개발/운영)에서도 코드 변경 없이 서버 설정만 바꾸면 됨
	----------------------------------------------------*/
	@Bean
	public DataSource dataSource() throws NamingException {
		JndiTemplate jndi = new JndiTemplate();
		return jndi.lookup("java:comp/env/jndi/mysql", DataSource.class);
	}

	/*----------------------------------------------------
	 2) 트랜잭션 매니저 등록 (PlatformTransactionManager)
	    - 스프링은 JDBC/MyBatis/Hibernate/JPA 등 기술이 달라도
	      "일관된 방식"으로 트랜잭션을 처리할 수 있게 해준다.
	    - 개발자는 자신이 쓰는 기술에 맞는 TransactionManager를 빈으로 등록하면 된다.

	    [왜 MyBatis도 DataSourceTransactionManager를 쓰나?]
	    - MyBatis는 내부적으로 JDBC를 사용해서 DB를 다룬다.
	    - 그래서 JDBC와 동일하게 DataSourceTransactionManager로 트랜잭션을 관리할 수 있다.

	    [핵심]
	    - 다양한 트랜잭션 매니저들의 공통 최상위 타입이 PlatformTransactionManager
	----------------------------------------------------*/
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	/*----------------------------------------------------
	 3) SqlSessionFactory 등록 (MyBatis 실행의 기반 공장)
	    - MyBatis에서 쿼리를 실행하려면 SqlSession이 필요하고,
	      SqlSession을 만들기 위한 공장이 SqlSessionFactory이다.
	    - 여기서는 "mybatis-spring"이 제공하는 SqlSessionFactoryBean을 사용해서
	      MyBatis 설정 파일(config.xml)을 읽고, DataSource와 연결하여 Factory를 만든다.

	    [config.xml에는 주로 들어가는 것]
	    - MyBatis 공통 설정(환경, 타입별칭, 매퍼 연결 등)
	    - (프로젝트마다 구성은 다를 수 있음)
	----------------------------------------------------*/
	@Bean
	public SqlSessionFactory sqlSessoinFactory(DataSource dataSource) throws Exception {

		// mybatis-spring이 제공하는 SqlSessionFactoryBean을 이용해서 설정 xml을 로드
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

		// 클래스패스 경로의 MyBatis 설정 파일 지정
		sqlSessionFactoryBean.setConfigLocation(
				new ClassPathResource("com/ch/shop/config/mybatis/config.xml")
		);

		// DB 연결 자원 주입
		sqlSessionFactoryBean.setDataSource(dataSource);

		// 최종적으로 SqlSessionFactory 생성/반환
		return sqlSessionFactoryBean.getObject();
	}

	/*----------------------------------------------------
	 4) SqlSessionTemplate 등록 (스프링-마이바티스에서 권장하는 실행 객체)
	    - 순수 MyBatis에서는 SqlSession을 직접 열고 닫으며 쿼리 수행을 했지만,
	      mybatis-spring에서는 SqlSessionTemplate을 사용한다.
	    - SqlSessionTemplate은 스프링 트랜잭션과 잘 결합되도록 만들어져 있어서
	      "세션 관리 + 트랜잭션 연동"이 더 안정적이고 일관되게 동작한다.

	    [관계]
	    SqlSessionTemplate은 내부적으로 SqlSessionFactory를 사용한다.
	----------------------------------------------------*/
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessoinFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessoinFactory);
	}	
	
	
}