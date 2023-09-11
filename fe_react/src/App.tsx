import "./App.css";
import { Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import PrivateRoute from "./router/PrivateRoute";
import ListPost from "./pages/ListPostPage";
import PostDetail from "./components/PostDetail";
import AuthorPage from "./pages/AuthorPage";
import AuthorInfo from "./components/Author/AuthorInfo";
function App() {

  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/" element = {<PrivateRoute><ListPost/></PrivateRoute>} />
      <Route path="/post_detail/:id" element = {<PrivateRoute ><PostDetail/></PrivateRoute>} />
      <Route path="/author/:author_name" element = {<PrivateRoute childrenHeader={<AuthorInfo />}><AuthorPage/></PrivateRoute>} />
  
    </Routes>
  );
}

export default App;
