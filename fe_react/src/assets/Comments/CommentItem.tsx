import { Avatar } from "antd";
import { CommentInfoItem, CommentItemStyled } from "../../styled/Commentstyled";
import {
  CommentOutlined,
  DislikeOutlined,
  LikeOutlined,
  RetweetOutlined,
} from "@ant-design/icons";
import { formatDateString } from "../../utils/fnhelper";
import { useState } from "react";
import PostComment from "./PostComment";

interface CommentProps {
  username?: string;
  content: string;
  date: string;
  src: string;
  handleSection: (section: string) => void;
  isReply?: boolean;
  idComment: string,
  setActiveReplyIndex: React.Dispatch<React.SetStateAction<string>>,
  activeReplyIndex: string

}

const CommentItem = ({
  username,
  content,
  date,
  src,
  // handleSection,
  isReply,
  idComment,
  setActiveReplyIndex,
  activeReplyIndex
}: CommentProps) => {
  const [dataReply, setDataReply] = useState("")

  function addReply() {
    setActiveReplyIndex(idComment)
  }

  function handleCreateReply() {

  }
  return (
    <CommentItemStyled style={isReply ? { marginLeft: 35, marginTop: 20 } : {}}>
      <Avatar
        src={src}
        style={{ minWidth: 30, height: 30, display: "inline-block" }}
      />
      <CommentInfoItem>
        <div className="username_comment">
          <span>{username} </span>
          <span className="content-comment">{content}</span>
        </div>

        <div className="sub-content">
          <div className="comment-action">
            <span className="icon">
              <LikeOutlined /> Like
            </span>
            <span className="icon">
              <DislikeOutlined /> Dislike
            </span>
            <span className="icon" 
            // onClick={() => handleSection("text_area")}
            onClick={addReply}
            >
              <CommentOutlined /> Reply
            </span>
            <span className="icon">
              <RetweetOutlined /> Share
            </span>
          </div>

          <div className="time-comment">
            <span>{formatDateString(date)}</span>
          </div>
        </div>
        {activeReplyIndex === idComment &&  <div className="ml10">
          <PostComment
                  handleCreateComment={handleCreateReply}
                  comment={dataReply}
                  setComment={setDataReply}
                  src={""}
                  isReply = {true}
          />
        </div>}
       
      </CommentInfoItem>
    </CommentItemStyled>
  );
};

export default CommentItem;
