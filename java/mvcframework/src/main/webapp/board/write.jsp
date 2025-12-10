<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap + Summernote</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap 4.6 CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

  <!-- jQuery (full 버전만 사용) -->
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

  <!-- Bootstrap 4.6 JS (bundle 안에 Popper 포함) -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

  <!-- Summernote -->
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>

  <script>
  $(()=>{	  
    $('#content').summernote();
    
    //등록버튼에 ... 
    document.getElementById("bt_regist").addEventListener("click", function(){
      let form1 = document.getElementById("form1");
      form1.action = "/board/regist.do";   
      form1.method = "post";
      form1.submit(); 
    });
  });
  </script>
</head>
<body>

<div class="container">
  <h2>Stacked form</h2>

  <form id="form1">
  <div class="form-group">
    <label for="title">제목:</label>
    <input type="text" class="form-control" id="title" placeholder="제목 입력" name="title">
  </div>
  
  <div class="form-group">
    <label for="writer">작성자:</label>
    <input type="text" class="form-control" id="writer" placeholder="작성자 입력" name="writer">
  </div>    

  <div class="form-group">
    <label for="content">내용:</label>
    <textarea class="form-control" id="content" placeholder="내용 입력" name="content"></textarea>
  </div>    

  <button type="button" id="bt_regist" name="bt" class="btn btn-primary">글 등록</button>
  <button type="button" name="bt" class="btn btn-success">목록</button>
</form>
</div>

</body>
</html>