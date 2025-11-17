package dbapp.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OraTest {
    // 자바 언어로 오라클 서버에 접속하여 insert문을 실행해보기
    public static void main(String[] args) {
    	
        // 오라클 서버 접속 정보
        String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1"; // Docker Oracle
        String user = "system";    // 또는 "java"
        String password = "Oracle1234!"; // 또는 "1234"
        
        PreparedStatement pstmt = null ; // 파이널리에서 닫아야하니까 try밖으로 꺼내야함 
        Connection con =null; 
        try {
            // 
            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection(url, user, password);

            //
            if (con != null) {
                System.out.println(" 오라클 접속 성공!");
                //접속에 성공하였으므로, 레코드 한건 넣어보자... 
                String sql = "INSERT INTO student(student_id, id, pwd, name)";
                		sql += " values(seq_student.nextval, 'kim','0000','tiger')";
                		
                System.out.println(sql);
                
                // 아직까지는 쿼리문을 준비만 하고, 아직 실행은 안한 상태 
                //자바의 데이터베이스 연동기술을 가리켜 JDBC라 하며, 주로 java.sql패키지에서 지원함 
                // jdbc 관련 객체 중 PreparedStatement 인터페이스가 쿼리문을 수행하는 역할을 함 
                
                pstmt = con.prepareStatement(sql);// 쿼리문을 수행할 인터페이스메모리에 올라옴 
                // 수행 
                // executeUpdate()메서드는 실행 후 두가지 경우의 수를 반환한다 
                // 성공시 이 쿼리문 실행에 의해 영향을 받은 레코드 수가반환(Insert에 의해 들어가는 레코드 1건 ) 
                // 따라서 성공시 1 반환 
                // 실패시에는 0 반환 
                int result = pstmt.executeUpdate(); // DML(insert, update, delete) 수행 
                String msg = null;
                
                if(result>0) {
                	msg="등록성공"; 
                } else { msg= "등록실패";}
                System.out.println(msg);
                // 삼항 연산자 
                
                msg = (result>0)? "등록성공" : "등록실패" ; 
                
            } else {
                System.out.println(" 접속 실패...");
            }
            // 작업이 끝나면 연결되어있던 데이터베이스 커넥션은 반드시 끊어야한다 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {// try든 catch든 닫아야하니까 finally에서 처리 
        	try {
        		con.close();
        		} catch(Exception e){
        			e.printStackTrace();
        			}
        	}
        }
    }




