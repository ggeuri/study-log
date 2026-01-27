import Header from "./Header"
import Footer from "./Footer"
import { Outlet } from "react-router-dom"
// 실제적으로 공통적인 레이아웃 머리/몸/푸터 

export default function Layout(){
    return(
        <div>
            <Header></Header>
            <main>
                <Outlet/> {/* 여기 부분이 페이지별로 교체되는 영역 */}
            </main>
            <Footer></Footer>
        </div>
    )

}