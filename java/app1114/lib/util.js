// 자주자주쓸것. 나만의 라이브러리 

/* 매개변수가 10보다 작은 1자리수라면 앞에 문자'0' 붙이기 */

function getZero(n){
    let result = n;
    if(n<10){
        result = "0" + n; 
    }
    return result;
}