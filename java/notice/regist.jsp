<%@ page contentType="text/html; charset=UTF-8"%>

<!--  위의 페이지 지시 영역은 jsp가 Tomcat에 의해 서블릿으로 코딩되어질때 
text/html 부분은 response.setContentType("test/html"); 
charset=UTF-8 response.setCharacterEncoding("utf-8");-->

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #04AA6D;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
</head>
<body>

	<div class="container">
		<form id = "form1">
			<input type="text" id="fname" name="title" placeholder="제목 입력..."> 
			<input type="text" id="lname" name="writer" placeholder="작성자 입력.."> 
			<textarea id="subject" name="content" placeholder="내용을 입력하세요.." style="height: 200px"></textarea>
			<input type="button" value="Submit" onClick="regist()">
		</form>
	</div>

</body>
<script>
function regist(){
	// JS는 DB와의 통신자체가 막혀있기때문에 직접 DB에 쿼리문을 날리는 것이 아니라 
	// 톰캣과 같은 웹컨테이너(서버)에게 부탁함 ! 
	let form1 = document.getElementById("form1");
	form1.action="/notice/regist"; // 서블릿주소
	form1.method="post"; 
	// Get,Post ? HTTP프로토콜은 머리와 몸으로 데이터 구성하여 통신하는규약 
	// 이때, 서버로 전송할 데이터가 양이 많거나 노출되지않으려면 편지지에 해당하는 POST방식을 이용한다. 
	// 반면, 서버로 전송할 데이터의 양이 적거나 노출되어도 상관없을 경우, 편지 봉투에 해당하는 GET방식을 이용한다. 
	form1.submit();//전송발생

}
</script>
</html>
