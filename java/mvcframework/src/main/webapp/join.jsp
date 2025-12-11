<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <script>
  $(()=>{
	  $("button").click(()=>{
		  alert("원");
	  });
  });
  
  </script>

</head>
<body>

<div class="container">
  <h2>사원등록</h2>
  <form >
    <div class="form-group">
      <label for="email">Email:</label>
      <input type="email" class="form-control" id="email" placeholder="부서번호l" name="email">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="email" class="form-control" id="pwd" placeholder="부서명" name="pswd">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="email" class="form-control" id="pwd" placeholder="부서위" name="pswd">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="email" class="form-control" id="pwd" placeholder="사원번호" name="pswd">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="email" class="form-control" id="pwd" placeholder="사원명" name="pswd">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="email" class="form-control" id="pwd" placeholder="급" name="pswd">
    </div>
    <button type="button" class="btn btn-primary">Submit</button>
  </form>
</div>

</body>
</html>
