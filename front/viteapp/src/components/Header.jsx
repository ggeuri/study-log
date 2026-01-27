import {NavLink} from "react-router-dom";
import styles from "./Header.module.css";

export default function Header(){
    return(
        <header className ={styles.headerStyle}>
        <div>My App</div>
        <nav>
            <NavLink to="/" end>메인</NavLink>
            <NavLink to="/signup">회원가입</NavLink>
            <NavLink to="/login">로그인</NavLink>
        </nav>
        </header>
    );
}