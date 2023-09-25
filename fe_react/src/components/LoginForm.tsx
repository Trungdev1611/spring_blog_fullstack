
import { Button, Form, Input } from 'antd';
import { Apiclient } from '../apis/config';
import { useNavigate } from 'react-router-dom';
import { showNotification } from '../utils/Notifi';
type valueForm = {
    username: string,
    password: string
}
const LoginForm = () => {
    const navigate = useNavigate()
    const onFinish = async (values: valueForm) => {
        console.log('Success:', values);
        try {
          const  res = await Apiclient.post(`/v1/auth/login`, values)
          if(res?.status) {
            showNotification("success", "Đăng nhập thành công")
            localStorage.setItem("token", res.data)
            navigate("/")
          }
          else {
            throw new Error()
          }
        } catch (error) {
          //console.log("error")
        }
       
       
      };

  return (
    <Form
    name="register"
    onFinish={onFinish}
    autoComplete="off"
    layout ="vertical"
    className='form-register'
    size="large"
  >
    <Form.Item
      label="Username"
      name="username"
      rules={[{ required: true, message: 'Please input your username!' }]}
    >
      <Input />
    </Form.Item>

    <Form.Item
      label="Password"
      name="password"
      rules={[{ required: true, message: 'Please input your password!' }]}
    >
      <Input.Password />
    </Form.Item>

      <Button type="primary" htmlType="submit" style={{marginTop: 16}}>
        Submit
      </Button>
  </Form>
  )
}

export default LoginForm
