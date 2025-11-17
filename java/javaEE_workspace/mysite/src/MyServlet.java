// javaEE기반으로 웹애플리케이션에서 실행될 수 있는 특수한 클래스를 사용해야 하는데, 이러한 서버에서만 해석 및 실행되어지는 클래스를 가리켜 서블릿(Servlet)이라고 함 
// 그리고 javaEE 기반의 웹애플리케이션의 구성 디렉토리는 javaEE 스펙으로 정해져있기때문에 반드시 정해진 디렉토리에 .class, .jar 등을 위치시켜야한다.
// 정해진 디렉토리 

// WEB-INF : 웹브라우저 통해서는 접근못하는 보안된 디렉토리 
    // classes : 컴파일된 클래스들 
    // lib : .jar들이 위치함.  

// 아래의 클래스가 javaee 서버에서 실행되려면 반드시 서블릿 클래스를 상속받아야한다. 
import javax.servlet.http.HttpServlet; //아직은 못가져온다 

class MyServlet extends HttpServlet{ //HttpServlet은 내가 쓰는 서버에 들어있는거임 가져와야함 
    // /Users/rimu/apache-tomcat-9.0.112/lib/servlet-api.jar에 있다 얘를 lib파일에 넣어줌 
    String name = "puppy";
}

// 일반파일은 path, classpath는 환경변수에 등록해라 .. 이거 이따가 혼자합시당 
