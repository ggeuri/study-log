import uiStyles from "../components/ui/Input.module.css";

export default function Login(){//default가 있다는건 이 안에 모듈 1개라는 뜻 
    return (
        <div style={{maxWidth:420, marin:"0 auto"}}>
            <div>
            <h2>로그인</h2>
            <form action="" style ={{display:"flex", flexDirection:"column",gap:10}}>
                <input type="text" placeholder="아이디 입력" className={uiStyles.inputStyle}/>
                <input type="password" placeholder="패스워드 입력" className={uiStyles.inputStyle}/>
                <button type="button" className={uiStyles.primaryBtn}>로그인</button>
            </form>
            </div>
        </div>
    )
}
