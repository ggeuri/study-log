<%@ page contentType="text/html; charset=UTF-8"%>
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
			
		}
		//요청준비 
		xhttp.open("POST", "/ajax/async_regist.jsp"); // 어떤서버의주소에 요청시도하고 어떤 HTTP메서드로  요청시도할지 결정하는 메서드 
		
		//브라우저 요청
		xhttp.send();
		
	
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
            <button type="button">sync</button>
            <button type="button">async</button>
        </form>
    </div>
    <div class ="content"></div>
</div>
</body>
</html>