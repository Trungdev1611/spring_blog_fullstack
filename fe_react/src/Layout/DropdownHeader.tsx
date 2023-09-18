import { Dropdown, Input, MenuProps, Radio } from "antd";
import TextArea from "antd/es/input/TextArea";
import { useState } from "react";
import ModalCommon from "../components/common/ModalCommon";

const DropdownHeader = () => {
  const [showModal, setShowModal] = useState(true);
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

  function handleOpenModalWriteBlog() {
    setShowModal(true);
  }

  function handleOk() {

    handleCancel()
  }

  function handleCancel() {
    setShowModal(false)

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
      <ModalCommon
        title="Thêm mới bài viết"
        open={showModal}
        onOk={handleOk}
        onCancel={handleCancel}
        width={700}
      >
        <div className="grid-container">
          <div className="grid-item">
            <div>Tiêu đề bài viết</div>
            <div>
              <Input placeholder="Basic usage" />
            </div>
          </div>
          <div className="grid-item blog-type">
            <div>Loại bài viết</div>
            <Radio.Group
            //   onChange={onChange} value={value}
            >
              <Radio value={1}>Public</Radio>
              <Radio value={2}>Private</Radio>
            </Radio.Group>
          </div>

          <div className="grid-item body-blog">
            <div>Nội dung bài viết</div>
            <TextArea rows={8} placeholder="maxLength is 6" maxLength={6} />
          </div>
        </div>
      </ModalCommon>
    </div>
  );
};

export default DropdownHeader;
