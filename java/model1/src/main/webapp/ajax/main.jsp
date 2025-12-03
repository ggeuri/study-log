<%@page import="com.ch.model1.dto.Member2"%>
<%@page import="java.util.List"%>
<%@page import="com.ch.model1.board.repository.Member2DAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%! 
Member2DAO dao = new Member2DAO();
%>
<% List<Member2> memberList = dao.selectAll();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .container{
        background-color: aqua;
        width: 650px;
        height: 500px;
        display: flex;
        margin: auto;
        border: solid 3px black;
    }
    
    .aside{
        width: 150px;
        height: 100%;
        background-color: rgb(255, 255, 255);
    }

    .aside input{
        margin-top: 2px;
        margin-left: 2px;
        width: 90%;
        align-items: center;
    }
    
    .aside button{
        width: 40%;
        margin-top: 2px;
        margin-left: 2px;
    }

    .content{
        width: 500px;
        height: 100%;
        background-color: rgb(122, 122, 122);
    }
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	function sendAsync(){
		//지금까지는 동기방식으로 서버에 요청 시도, 그 결과로 html을 가져와서 브라우저 화면에 출력함으로써 유저가 보기엔 새로고침 현상이 발생하게됨
		// 따라서 현재페이지는 그대로 유지하고 백그라운드에서 크롬과 같은 웹브라우저가 대신 서버와의 통신을 담당하고 그 시간동안 자바스크립트는 원래하고자했던 로직을 그대로 수행 
		//추후 서버로부터 응답이 오면 크롬 브라우저는 자바스크립트에게 보고를하게되며, 이때 서버로부터 가져온 순수데이터를(HTML아님!) 자바스크립트에 전달. 그러면 자바스크립트는 순수 데이터를 이용하여 화면에 동적 출력
		// 새로고침 X 
		
		let xhttp = new XMLHttpRequest(); //주의 이 객체가 서버로 요청 떠나는게 아니라 크롬브라우저가요청시도 
		
		
		//크롬등의브라우저가 서버로부터 응답받을때발생하는 이벤트처리하는 속성
		// 브라우저가 서버로부터 응답받으면 onload에 지정한 콜백함수를 자동 호출(이때 호출 주체는 js )
		xhttp.onload=function(){
			
			//문자열로오니까 불편함 해결책? 
			// 문제점) 앞으로 우리는 REST API 다룰 것. 우리의 서버에 요청 시도하는 다양한 종류의 클라이언트들에게 데이터를 제공해줄예정, 이때 사용할 데이터형식은 
			// 전세계적으로 xml, json 
			
			//전세계 개발자들이 주로 사용하는 표준형식 데이터 사용하자 (json). 어떤 문자열이 JSON표기법 준수하여 작성되어있다면 JS는 내장객체인 JSON내장객체를 이용하여 문자열을 실제 해석하여JS객체리터럴로 전환 
			let obj = JSON.parse(xhttp.responseText); //JSON구분형식맞으면 전환해줌 
			console.log("email은 ", obj.email);
			//정말로 obj가 자바스크립트의 인스턴스라면 객체.속성 접근가능해야함 

		}
		
		//요청준비 
		xhttp.open("POST", "/ajax/async_regist.jsp"); // 어떤서버의주소에 요청시도하고 어떤 HTTP메서드로  요청시도할지 결정하는 메서드 

		//Http메서드가 post인 경우 헤더값을 다음과 같이 세팅 (헤더에 대한 설정은 반드시open 메서드 이후에 작성 )
		xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		
		//브라우저 요청
		xhttp.send("id="+$("input[name='id']").val()+"&name="+$("input[name='name']").val()+"&email="+$("input[name='email']").val());
		
	
	}
    // ë¬¸ì ë¡ëëë©´ ëê°ì ë²í¼ì ëí´ ì´ë²¤í¸ ì°ê²° (íì´íí¨ì)
    $(() => {
        // ëê¸°ë²í¼ì í´ë¦­ ì´ë²¤í¸ ì°ê²° 
        $($("form button")[0]).click(()=>{                                                                                               
            // alert("ë");
            $("form").attr({
                method: "post", 
                action: "/ajax/regist.jsp" 
            });
            $("form").submit();
        });

       //  동기 
       // 동기는 전통적으로 순서 지키는 실행방식임 . 
       // 장점 : 순서에 의해 실행됨. 이전단계실행완료되어야 나중순서 로직이 실행 (안정적. 순서엉키지않음) / (만일 앞선 실행부가 반복문이나 대기상태에 빠질경우 후순위로직은 실행이 지연되거나 계속 기다리는 현상발생)
       // 비동기
       // 비동기는 순서를 지키지 않는 방식임. 앞선 실행부가 대기상태에 빠지더라도 후순위 실행이 영향받지않음
       // 서버로부터 응답받는 데이터형식이 HTML이 아니므로 새로고침현상 발생하지않으나 페이지 디자인을 동적으로 처리하는데 많은 시간과 노력 필요(렌더링)
        // 참고로 페이지를 동적으로 처리하는 양이 너무 가혹하여 페이스북 개발자들이 만들어낸 js기반의 프레임워크가 React.js임 . 
        $($("form button")[1]).click(()=>{
        	sendAsync();
        });
    })
</script>
</head>
<body>
<div class = "container">
    <div class = "aside">
        <form action="">
            <input type="text" name = "id" placeholder="Your ID...">
            <input type="text" name = "name" placeholder="Your NAME...">
            <input type="text" name = "email" placeholder="Your EMAIL...">
            <button type = "button">sync</button>
            <button type = "button">async</button>
        </form>
    </div>
    <div class ="content">
    <table width = "100%" border="1px" >
	    <thead>
	    	<tr>	    
			    <th>id</th>
			    <th>name</th>
			    <th>email</th>
	    	</tr>
	    </thead>
	    <tbody>
	    <%for (int i = 0; i < memberList.size(); i++){ %>
		    <%Member2 dto = memberList.get(i); %>
		    <tr>
			    <td><%=dto.getId() %></td>
			    <td><%=dto.getName() %></td>
			    <td><%=dto.getEmail() %></td>
		    </tr>
		    <%} %>
	    </tbody>
	    
    
    </table>
    </div>
</div>
</body>
</html>