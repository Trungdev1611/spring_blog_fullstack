import { notification } from "antd";
import { createContext } from "react";



type NotificationType = 'success' | 'info' | 'warning' | 'error';
type ToastContextProps = {
  children: JSX.Element
} 
export const ToastContext = createContext<(type: NotificationType) => void>(() => {});

export  const ToastContextCom = ({ children }: ToastContextProps) => {
    const [api, contextHolder] = notification.useNotification();

    const openNotificationWithIcon = (type: NotificationType, desc?: string) => {
      api[type]({
        message: type === "success" ? "Thành công" : "Lỗi rồi",
        description:desc || "Yêu cầu được thực hiện thành công"
          ,
      });
    };
  return (
    <ToastContext.Provider value={openNotificationWithIcon}>
      {contextHolder}
      {children}
    </ToastContext.Provider>
  )
}

