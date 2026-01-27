const BASE_URL = "http://localhost:9993/api/auth"; //Spring api서버주소

//로그인요청 
//payload는 js객체 리터럴 ex) {homepageId : "gg", password : "1234"}

export function loginRequestWithJson(payload){

    fetch(`${BASE_URL}/login`,{
        method: "POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify(payload),
        credentials:"include" //비동기방식에서는 개발자가 요청시 쿠키 포함하여 전송함 선언 
    })
    .then(res =>{
        if(!res.ok) throw new Error("로그인에러");
        return res.json(); //promise반환하면서 resolve()호출
    })
    .then(data =>{
        console.log("로그인결과 사용자정보는",data);
    })
    .catch(err => console.log(err));
}


// 로그인 요청 (JSON문자열 전송 말고 form)
export function loginRequest(payload){
    const params = new URLSearchParams();
    params.append("homepageId",payload.homepageId);
    params.append("password", payload.password);

    fetch(`${BASE_URL}/login`,{
        method:"POST",
        headers:{"Content-Type":"application/x-www-form-urlencoded"},
        credentials:"include",
        body:params.toString(), //객체라 스트링으로 바꿔서 전송 
    })
    .then(res =>{
        if(!res.ok) throw new Error("로그인에러");
        return res.json(); //promise반환하면서 resolve()호출
    })
    .then(data =>{
        console.log("로그인결과 사용자정보는",data);
    })
    .catch(err => console.log(err));
}