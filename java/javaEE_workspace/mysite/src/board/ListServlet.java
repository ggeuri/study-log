package board;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;

public class ListServlet extends HttpServlet{
    // 웹브라우저로 접근하는 클라이언트들에게 메시지 출력하기 

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        // javaSE의 스트림 객체를 사용
        PrintWriter out = response.getWriter(); // 응답객체로부터 스트림을 얻는다. = 클라이언트에게 문자열 출력 위해
        out.print("this is my name");
    }
}
