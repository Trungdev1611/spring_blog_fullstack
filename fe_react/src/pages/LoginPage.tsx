import { Heading, LoginWrapper, RegisterContainer,  TypeLogin } from "../styled/common";
import { Divider } from "antd";
import {Link} from 'react-router-dom'
import { GooglePlusOutlined, AppleOutlined } from "@ant-design/icons";
import LoginForm from "../components/LoginForm";
import { useEffect } from "react";

const LoginPage = () => {
  useEffect(() => {
    localStorage.removeItem("token")
    localStorage.removeItem("userInfo")
  }, [])
  return (
    <div>
      <Heading>Đăng nhập</Heading>
      
      <RegisterContainer $isLogin = {true}>
        <div style={{width: "80%", display: "flex", justifyContent: "center"}}>
          <LoginForm />
        </div>
        <Divider plain>HOẶC</Divider>
        
        <LoginWrapper >
          <TypeLogin>
            <div className="icon"><GooglePlusOutlined /></div>
            <div className="text">Đăng nhập bằng Google</div>
          </TypeLogin>
          <TypeLogin>
            <div className="icon"><AppleOutlined/></div>
            <div className="text">Đăng nhập bằng Apple</div>
          </TypeLogin>
          <div className="description">
            Nếu tiếp tục bằng Google hoặc Apple, bạn đồng ý với{" "}
            <Link to={"/abc"}>Điều khoản Dịch vụ</Link> và đã đọc <Link to={"/abc"}>Chính sách Bảo mật</Link>.
          </div>
        </LoginWrapper>
      </RegisterContainer>
    </div>
  );
};

export default LoginPage;