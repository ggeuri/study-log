package com.ch.mvcframework.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfig {
	private static MybatisConfig instance; 
	private SqlSessionFactory sqlSessionFactory ;
//	Mybatis설정파일은 프로그래밍 아니라 단순 설정 리소스니까 리소스 해석해줄 객체 필요 
	
	public static MybatisConfig getInstance() {
		if(instance==null){instance = new MybatisConfig();}
		return instance;
	}
	
	private MybatisConfig() {
		
		try {
			String resource = "com/ch/mvcframework/mybatis/config.xml"; // 패키지안에있는애가 java아니면 다 디렉토리취급해서 .을 /로 바꿔서 써줘야함 
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			//마이바티스 이용하면 개발자는 더이상 JDBC를 직접사용하여 데이터베이스 연동코드 작성할 필요없음 
			// 이때 개발자가 쿼리문 수행하려면 마이바티스가 제공해주는 SqlSession이용. SqlSessionFactory 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//팩토리로부터 쿼리문실행에필요한 sqlsession 객체 가져갈 수 있도록. 참고로 SqlSession은 이미 접속 정보 가지고 있음 
// 쿼리문도 실행할 수 있는 객체임.자바개발자는 기존의 JDBC코드에서 Connection,PreparedStatement를 직접 다루었던 비효율적 코드에서 벗어날 수 있다 
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
	public void release(SqlSession sqlSession) {
		if(sqlSession !=null) sqlSession.close();
	}
	
}
