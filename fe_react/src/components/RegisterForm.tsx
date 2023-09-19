
import { Button, Form, Input } from 'antd';
import { Apiclient } from '../apis/config';
import { useNavigate } from 'react-router-dom';
type valueForm = {
    username: string,
    password: string
}
const RegisterForm = () => {
    const navigate = useNavigate()
    const onFinish = async (values: valueForm) => {
      try {
        const  res = await Apiclient.post(`/v1/auth/register`, values)
        console.log("res", res)
        if(res?.status) {
          alert("Register success")
          navigate("/login")
        }
      } catch (error) {
        console.log("error", error)
        alert("register failed. Try again")
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

export default RegisterForm
