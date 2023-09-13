import { Avatar, Input } from "antd";
import { Button } from "antd";
import React from "react";

const PostComment = ({src, username}:{src:string, username:string}) => {
  return (
    <div>
      <h3>The Conversation (5)</h3>
      <Input.TextArea rows={4} placeholder="Leave your comment" style={{marginTop: 10}} />
      <div style={{ display: "flex", justifyContent: "flex-end" , alignItems: "center", marginTop: 20, gap: 20}}>
        {/* <span>{username}</span> */}
        <Avatar src={src}/>
        <Button
          size="large"
          style={{
            backgroundColor: "#d33a2c",
            color: "white",
          
          }}
        >
          Post Comment
        </Button>
      </div>
    </div>
  );
};

export default PostComment;
