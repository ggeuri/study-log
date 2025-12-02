<%@page import="com.ch.model1.dto.Board"%>
<%@page import="com.ch.model1.board.repository.BoardDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! 
BoardDAO boarDao = new BoardDAO();

%>
<%
//list.jsp로부터 전송되어온 파라미터인 board_id값을 이용하여 DAO에게 일시키자 

String board_id = request.getParameter("board_id"); 
Board board = boarDao.select(Integer.parseInt(board_id));

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

	$(function(){
		//서머노트 등장 ~ 
		$("#summernote").summernote({
			placeholder:"내용을 입력하세요"
			, height: 250
		});
		
		//글쓰기와 목록 버튼에 이벤트 연결 
		$("#bt_edit").click(function(){
			if(confirm("수정하시겠습니까?")){
				$("form").attr({
					action:"/board/edit",
					method: "POST"
				});	
				$("form").submit();
			}
		});
		
		$("#bt_del").click(function(){
			if(confirm("삭제하시겠습니까?")){
				$("form").attr({
					action:"/board/del",
					method: "POST"
				});	
				$("form").submit();
			}
		});
		
		$("#bt_list").click(function(){
			//글쓰기 폼 전송 
			location.href="/board/list.jsp";
		});
		
		//서머노트에 동적으로 데이터 넣기 
		$("#summernote").summernote("code", "<%=board.getContent()%>");
	});
	
</script>
</head>
<body>

<h3></h3>

<div class="container">
  <form>
    <input type="hidden" id="fname" name="board_id" value="<%=board.getBoard_id()%>">
    <label for="fname">제목</label>
    <input type="text" id="fname" name="title" value="<%=board.getTitle() %>">

    <label for="lname">작성자</label>
    <input type="text" id="lname" name="writer" value="<%=board.getWriter() %>">

    <label for="subject">내용</label>
    <textarea id="summernote" name="content" style="height:200px"></textarea>

    <input type="button" value="수정" id="bt_edit">
    <input type="button" value="삭제" id="bt_del">
    <input type="button" value="목록" id="bt_list">
  </form>
</div>

</body>
</html>
