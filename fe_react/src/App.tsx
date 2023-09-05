import "./App.css";
import { Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import PrivateRoute from "./router/PrivateRoute";
import ListPost from "./pages/ListPost";
function App() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/" element = {<PrivateRoute><ListPost/></PrivateRoute>} />
  
    </Routes>
  );
}

export default App;
