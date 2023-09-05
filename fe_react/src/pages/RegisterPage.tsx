import { Heading, LoginWrapper, RegisterContainer, TextTitle, TypeLogin } from "../styled/common";
import { Divider } from "antd";
import RegisterForm from "../components/RegisterForm";
import {Link} from 'react-router-dom'
import { GooglePlusOutlined, AppleOutlined } from "@ant-design/icons";

const RegisterPage = () => {
  return (
    <div>
      <Heading>Bắt Đầu</Heading>
      <TextTitle>
        Bạn cần tạo tài khoản WordPress.com. Đã có tài khoản?{" "}
        <span>Đăng nhập</span>
      </TextTitle>
      <RegisterContainer>
        <div style={{flex: 1, display: "flex", justifyContent: "flex-end"}}>
          <RegisterForm />
        </div>
        <Divider type="vertical" />
        
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

export default RegisterPage;
