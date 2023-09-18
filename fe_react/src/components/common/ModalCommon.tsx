import { Button } from "antd";
import { ModalCreatePost } from "../../styled/common";

interface ModalProps {
  open: boolean;
  onOk: () => void;
  onCancel: () => void;
  loading?: boolean;
  title: string;
  children: JSX.Element;
  width: number;
}
const ModalCommon = (props: ModalProps) => {
  const { onOk, onCancel, loading } = props;
  return (
    <ModalCreatePost
      {...props}
      footer={[
        <Button key="back" onClick={onCancel}>
          Cancel
        </Button>,
        <Button key="submit" type="primary" loading={loading} onClick={onOk}>
          Submit
        </Button>,
      ]}
    >
      {props.children}
    </ModalCreatePost>
  );
};

export default ModalCommon;
