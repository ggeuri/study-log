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
	function printList(commentList){
		// 댓글 목록 출력함수 
		let tag="<table width='100%' border='1px'>";
		tag+="<thead>";
		tag+="<tr>";
		tag+="<th>No</th>";
		tag+="<th>댓글내용</th>";
		tag+="<th>작성자</th>";
		tag+="<th>작성일</th>";
		tag+="</tr>";
		tag+="</thead>";
		tag+="<tbody>";
		let num = commentList.length; //게시물 수 담아놓고 -- 처리 
		for(let i = 0; i < commentList.length ; i++){
			let obj = commentList[i];
			tag+="<tr>";
			tag+="<td>"+(num--)+"</td>";
			tag+="<td>"+obj.msg+"</td>";
			tag+="<td>"+obj.reader+"</td>";
			tag+="<td>"+obj.writedate+"</td>";
			tag+="</tr>";
		}		
		tag+="</tbody>";
		tag+="</table>";
		$(".commentList").html(tag);
		
	}
	
	function getList(){
	//댓글 목록 비동기로 가져오기 .. 상세페이지 들어왔을때도 호출, 실시간 댓글을 등록할 때도 호출 
		let xhttp = new XMLHttpRequest();
		
		xhttp.onload=function(){
			
			let commentList = JSON.parse(this.responseText); //{\"resultMsg\":\"등록실패\"}는 문자열. json으로 바꾸고 키값으로 접근하면됨
			console.log("변환객체는 ", commentList);
			printList(commentList); 
		}
		xhttp.open("GET","/news/comment_list.jsp?news_id=<%=news_id%>");
		xhttp.send();//목록요청 
		
	}


	function registComment(){
		//댓글을 비동기적으로 .............  
		
		let msg = $("input[name='msg']").val(); 
		let reader = $("input[name='reader']").val(); 
		let news_id = $("input[name='news_id']").val(); 
		
		let xhttp = new XMLHttpRequest();
		xhttp.open("POST","/news/comment_regist.jsp");
		//비동기적으로 POST 요청하려면 헤더 필요해용 
		xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		//서버로부터 응답정보 도착했을때 익명함수 호출 
		xhttp.onload=function(){
			let obj = JSON.parse(this.responseText); //{\"resultMsg\":\"등록실패\"}는 문자열. json으로 바꾸고 키값으로 접근하면됨

			getList(); //등록된 결과물마저도비동기 요청 
		}
		xhttp.send("msg="+msg+"&reader="+reader+"&news_id="+news_id);//서버요청 
	
	}

	$(function(){
		//서머노트 등장 ~ 
		$("#summernote").summernote({
			placeholder:"내용을 입력하세요"
			, height: 250
		});
		
		$("#summernote").summernote("code", "<%=news.getContent()%>");
		
		getList();
		
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
  <input type="hidden" value = "<%=news.getNews_id()%>"  name = "news_id" >
  <input type="text" style="width:65%; background:white; " name = "msg" >
  <input type="text" style="width:20%; background:white;" name = "reader">
  <input type="button" value = "댓글등록" onClick="registComment()"></input>
  </div>
 <!--  댓글목록............-->
  <div class="commentList">  
  </div>
  
  </form>
</div>

</body>
</html>
