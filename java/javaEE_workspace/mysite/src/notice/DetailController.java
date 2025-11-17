package notice;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.PrintWriter;


public class DetailController extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{

        // 웹브라우저로 접근하는 유저에게 한글 메시지 출력하기 
    resp.setContentType("text/html; charset=UTF-8");
    resp.setCharacterEncoding("UTF-8");

    PrintWriter out = resp.getWriter();
    out.print("나의 서블릿으로 한글 출력");


    }
    
    
}
