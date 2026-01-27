import {NavLink} from "react-router-dom";
export default function Header(){
    return(
        <header>
        <div>My App</div>
        <nav>
            <NavLink to="/" end>메인</NavLink>
        </nav>
        </header>
    );
}