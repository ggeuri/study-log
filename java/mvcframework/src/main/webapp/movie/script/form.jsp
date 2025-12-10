<%@ page contentType="text/html; charset=UTF-8"%>
<%

/* 하나의 페이지로 모든 기능 디자인 합쳐놓은 프로그램 장단점 
장점 : 개발시간 단축 
단점 : 디자인 - 로직 일체화. 디자인 버리면 로직도 버려야댐 */
request.setCharacterEncoding("utf-8");
String movie = request.getParameter("movie");
out.print(movie);

String msg = "선택한 영화가 없음";

//재사용 가능... (Model)
if(movie !=null){
if(movie.equals("귀멸의칼날")){
	msg = "일본 애니메이션"; 
}else if (movie.equals("주토피아2")){
	msg = "디즈니 애니메이션"; 
}else if (movie.equals("위키드2")){
	msg = "뮤지컬 영화"; 
}
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function request(){
	document.querySelector("form").action="/movie/script/form.jsp"; //오 레전드 
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
<!-- 이 프로그램에 대해 유지보수성을 고려할 필요가 없을 정도로 간단한 기능으로 판단된다면 굳이 유지보수성을 염두한 자바클래스까지 도입할 필요 없음
따라서 스크립트만으로 해결 : 막개발 ㅋ  -->
<h3>
선택한 결과<br>
<span style="color:red">

당신이 선택한 영화는 <%=msg %>입니다.
</span>

</h3>
</body>
</html>