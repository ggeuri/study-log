import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter } from 'react-router-dom'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  // StrictMode는 개발모드에서만 동작하는 잠재적 문제 미리 잡아줌 (deprecated API, 오래된 LifeCycle메서드, 잘못된 위치의 side-effect 등)
  // UI영향 안주고, 실제운영환경에서는 완전히 제거돼서 별도 제거할 필요없음.
  <StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </StrictMode>
)
