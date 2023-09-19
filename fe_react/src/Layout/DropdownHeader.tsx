import { Alert, Dropdown, Form, Input, MenuProps, Radio, Spin } from "antd";
import TextArea from "antd/es/input/TextArea";
import { useState } from "react";
import ModalCommon from "../components/common/ModalCommon";
import { Apiclient } from "../apis/config";
import UseToast from "../Hooks/UseToast";

interface valueForm {
  heading: string;
  typepost: string;
  content: string;
  avatar: string;
}

const DropdownHeader = () => {
  const [showModal, setShowModal] = useState(false);
  const [loading, setLoading] = useState(false);
  const items: MenuProps["items"] = [
    {
      key: "1",
      label: <div className="dropdown-item">PopsCast</div>,
    },
    {
      key: "2",
      label: (
        <div className="dropdown-item" onClick={handleOpenModalWriteBlog}>
          Write for us
        </div>
      ),
    },
    {
      key: "3",
      label: <div className="dropdown-item">Advertise with us</div>,
    },
  ];
  const [form] = Form.useForm();
  function handleOpenModalWriteBlog() {
    setShowModal(true);
  }
  const showToast = UseToast()

  const onFinish = async (values: valueForm) => {
    // Xử lý dữ liệu ở đây
    setLoading(true);
    try {
      console.log("Received values:", values);
      values.avatar =
        "https://files.smashing.media/authors/hannah-milan-200px.jpg";
      const res = await Apiclient.post("/v1/posts/create_post", values);
      if(res.status) {
        showToast("success")
      }
      console.log(res);
    } catch (error) {
      console.log("error");
    } finally {
      handleCancel();
      setLoading(false);
    }
  };

  function handleOk() {
    form.submit();
  }

  function handleCancel() {
    form.resetFields();
    setShowModal(false);
  }
  return (
    <div>
      <Dropdown
        menu={{ items }}
        placement="bottom"
        overlayClassName="dropdowm-nav"
      >
        <li>More</li>
      </Dropdown>
      {loading ?   <Spin tip="Loading...">
            <Alert
              message="Đang gửi yêu cầu"
              description="Yêu cầu của bạn đang được gửi....."
              type="info"
            />
          </Spin> : <ModalCommon
        title="Thêm mới bài viết"
        open={showModal}
        onOk={handleOk}
        onCancel={handleCancel}
        width={700}
      >
        <div>
     
          <Form
            name="create_post"
            labelCol={{ span: 24 }}
            wrapperCol={{ span: 24 }}
            initialValues={{
              avatar:
                "https://files.smashing.media/authors/hannah-milan-200px.jpg",
            }}
            onFinish={onFinish}
            autoComplete="off"
            form={form}
          >
            <Form.Item
              label="Tiêu đề bài viết"
              name="heading"
              rules={[
                { required: true, message: "Vui lòng nhập tiêu đề bài viết" },
              ]}
            >
              <Input placeholder="Heading post..." />
            </Form.Item>

            <Form.Item
              label="Loại bài viết"
              name="typepost"
              rules={[
                { required: true, message: "Vui lòng chọn loại bài viết" },
              ]}
            >
              <Radio.Group>
                <Radio value={1}>Public</Radio>
                <Radio value={2}>Private</Radio>
              </Radio.Group>
            </Form.Item>

            <Form.Item
              label="Nội dung bài viết"
              name="content"
              rules={[
                { required: true, message: "Vui lòng nhập nội dung bài viết" },
              ]}
            >
              <TextArea rows={8} placeholder="Content post in here..." />
            </Form.Item>
          </Form>
        </div>
      </ModalCommon> } 
      
    </div>
  );
};

export default DropdownHeader;
