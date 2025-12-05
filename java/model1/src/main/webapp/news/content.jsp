<%@page import="com.ch.model1.dto.News"%>
<%@page import="com.ch.model1.board.repository.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! 
NewsDAO newsDAO = new NewsDAO(); 

%>
<%
//파라미터 넘겨받아 DAO에게 넘겨주면 끝임 
String news_id = request.getParameter("news_id");
News news =  newsDAO.select(Integer.parseInt(news_id));
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset = "utf-8">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

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
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
<script>
	function registComment(){
		//댓글을 비동기적으로 .............  
		
		let msg = $("input[name='msg']").val(); 
		let reader = $("input[name='reader']").val(); 
		
		let xhttp = new XMLHttpRequest();
		xhttp.open("POST","/news/comment_regist.jsp");
		//비동기적으로 POST 요청하려면 헤더 필요해용 
		xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		xhttp.send("msg="+msg+"&reader="+reader);//서버요청 
		
	
	}

	$(function(){
		//서머노트 등장 ~ 
		$("#summernote").summernote({
			placeholder:"내용을 입력하세요"
			, height: 250
		});
		
		$("#summernote").summernote("code", "<%=news.getContent()%>");
		
	});
</script>
</head>
<body>

<h3></h3>

<div class="container">
  <form>
    <label for="fname">제목</label>
    <input type="text" id="fname" name="title" placeholder="Your title..." value="<%=news.getTitle()%>">

    <label for="lname">작성자</label>
    <input type="text" id="lname" name="writer" placeholder="Your name.."  value="<%=news.getWriter()%>">

    <label for="subject">내용</label>
    <textarea id="summernote" name="content" placeholder="Write something.." style="height:200px"></textarea>

    <input type="button" value="글등록" id="bt_regist">
    <input type="button" value="목록" id="bt_list">
  </form>
  <form action="">
  <div>
  <input type="text" style="width:65%; background:white; " name = "msg" >
  <input type="text" style="width:20%; background:white;" name = "reader">
  <input type="button" value = "댓글등록" onClick="registComment()"></input>
  
  </div>
  
  </form>
</div>

</body>
</html>
