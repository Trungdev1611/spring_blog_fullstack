import ReactDOM from "react-dom/client";
import App from "./App.tsx";
import "./index.css";
import { BrowserRouter } from "react-router-dom";
import { ToastContextCom } from "./Context/ToastContextCom.tsx";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <BrowserRouter>
    <ToastContextCom>
      <App />
    </ToastContextCom>
  </BrowserRouter>
);
