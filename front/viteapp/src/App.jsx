import { Routes, Route } from 'react-router-dom'
import Layout from './components/Layout'
import Home from './pages/Home'
import Signup from './pages/Signup'
import Login from './pages/Login'

function App() {
  return(
  <Routes>
    <Route element={<Layout />}>
    <Route path="/" element={<Home />} />
    <Route path="/signup" element={<Signup />} />
    <Route path="login" element={<Login />} />
    </Route>

  </Routes>
  );
}
//export란 외부에서 이 파일 사용할 수 있도록 허용하되, 외부에서 접근할 이름은 App
export default App
