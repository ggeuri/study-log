<%@page import="com.ch.mvcframework.dto.Board"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
Board board = (Board)request.getAttribute("board");

%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap + Summernote</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


  <link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>

  <script>
  $(()=>{

    $('#content').summernote();
    $('#content').summernote("code","<%=board.getContent()%>");

    $("#bt_edit").click(()=>{
      if(confirm("수정하시겠어요?")){
        $("#form1").attr({
          action: "/board/edit.do",
          method: "POST"
        });
        $("#form1").submit();
      }
    });

    $("#bt_del").click(()=>{
      if(confirm("삭제하시겠어요?")){
        location.href = "/board/delete.do?board_id=<%=board.getBoard_id()%>";
      }
    });

    $("#bt_list").click(()=>{
      location.href = "/board/list.do";
    });
  });
  </script>
</head>
<body>

<div class="container">
  <h2>게시글 상세 / 수정</h2>

  <form id="form1">
    <div class="form-group">
      <input type="hidden" class="form-control" id="board_id"
             value="<%=board.getBoard_id() %>" name="board_id">
      <label for="title">제목:</label>
      <input type="text" class="form-control" id="title"
             value="<%=board.getTitle() %>" name="title">
    </div>

    <div class="form-group">
      <label for="writer">작성자:</label>
      <input type="text" class="form-control" id="writer"
             value="<%=board.getWriter()%>" name="writer">
    </div>

    <div class="form-group">
      <label for="content">내용:</label>

      <textarea class="form-control" id="content" name="content"></textarea>
    </div>

    <!-- detail.jsp라면 '글 등록'은 굳이 필요 없어서 주석 처리해도 됨 -->
    <!-- <button type="button" id="bt_regist" name="bt" class="btn btn-secondary">글 등록</button> -->

    <button type="button" name="bt" class="btn btn-secondary" id="bt_edit">수정</button>
    <button type="button" name="bt" class="btn btn-danger" id="bt_del">삭제</button>
    <button type="button" name="bt" class="btn btn-secondary" id="bt_list">목록</button>
  </form>
</div>

</body>
</html>