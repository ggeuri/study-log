<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function request(){
	document.querySelector("form").action="/food.do";  
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
<select name ="food">
<option>보쌈</option>
<option>무생채</option>
<option>백김치</option>
</select>
<button type="button">피드백요청</button>
</form>

</body>
</html>