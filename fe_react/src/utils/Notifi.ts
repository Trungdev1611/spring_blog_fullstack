import {notification } from 'antd';

type Notify = "success" | "info" | "warning" | "error"

export const showNotification = (typeNoti: Notify, desc: string) => {
    notification.open({
      message: typeNoti ==="success" ? "Success" : "Error",
      description: desc,
      onClick: () => {
        console.log('Notification Clicked!');
      },
      type: typeNoti
    });
  };

