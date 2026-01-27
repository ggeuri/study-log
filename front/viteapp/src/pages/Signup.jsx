import uiStyles from "../components/ui/Input.module.css";

export default function Signup(){
    return(
        <div style={{ maxWidth: 420, margin: "0 auto" }}>
            <div>
                <h2>회원가입</h2>
                <form action=""style ={{display:"flex", flexDirection:"column",gap:10}}>
                    <input type="text" placeholder="아이디" className={uiStyles.inputStyle}/>
                    <input type="password" placeholder="비밀번호" className={uiStyles.inputStyle}/>
                    <input type="text" placeholder="이름" className={uiStyles.inputStyle}/>
                    <button type="button" className={uiStyles.primaryBtn}>가입하기</button>
                </form>
            </div>
        </div>
    );
}
