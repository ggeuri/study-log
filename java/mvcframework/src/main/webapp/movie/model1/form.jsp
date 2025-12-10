<%@page import="com.ch.mvcframework.movie.model.MovieManager"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! MovieManager manager = new MovieManager(); %>
<%

request.setCharacterEncoding("utf-8");
String movie = request.getParameter("movie");
out.print(movie);

//영화에 대한 판단을 해주는 코드 별도의 모델 객체로분리 (이유? 어떤 플랫폼에서도 재사용가능하게)
String msg = manager.getAdvice(movie);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function request(){
	document.querySelector("form").action="/movie/model1/form.jsp";  
	document.querySelector("form").method="POST";
	document.querySelector("form").submit();
}

addEventListener("load",function(){
	document.querySelector("button").addEventListener("click",()=>{
		request();
	});
	
})

</script>
</head>
<body>
<form>
<select name ="movie">
<option>주토피아2</option>
<option>위키드2</option>
<option>귀멸의칼날</option>
</select>
<button type="button">피드백요청</button>
</form>

<h3>
선택한 결과<br>
<span style="color:red">

당신이 선택한 영화는 <%=msg %>입니다.
</span>

</h3>
</body>
</html>