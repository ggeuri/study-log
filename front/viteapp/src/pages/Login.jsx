import uiStyles from "../components/ui/Input.module.css";
import {loginRequest} from "../api/auth.js" //default 대표가아니니까 {} 
import { useRef } from "react"; //임포트 필수.. 

export default function Login(){//default가 있다는건 이 안에 모듈 1개라는 뜻 
    const homepageIdRef = useRef(null); //useRef선언 = 객체. current안에 데이터 얻을수 있음 
    const passwordRef = useRef(null);
    //로그인 요청메서드 정의
    const login =() =>{


        const payload = {
            //사용자가 입력한 ...
            homepageId : homepageIdRef.current.value,
            password: passwordRef.current.value
        };

        loginRequest(payload);

    }
    
    return (
        <div style={{ maxWidth: 420, margin: "0 auto" }}>
            <div>
            <h2>로그인</h2>
            <form action="" style ={{display:"flex", flexDirection:"column",gap:10}}>
                <input type="text" placeholder="아이디 입력" className={uiStyles.inputStyle} ref={homepageIdRef}/>
                <input type="password" placeholder="패스워드 입력" className={uiStyles.inputStyle} ref={passwordRef}/>
                <button type="button" onClick={login} className={uiStyles.primaryBtn}>로그인</button>
            </form>
            </div>
        </div>
    )
}
