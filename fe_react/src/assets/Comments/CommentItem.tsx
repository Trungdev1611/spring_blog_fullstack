import { Avatar } from "antd";
import { CommentInfoItem, CommentItemStyled } from "../../styled/Commentstyled";
import { CommentOutlined, DislikeOutlined, LikeOutlined, RetweetOutlined } from "@ant-design/icons";
import { formatDateString } from "../../utils/fnhelper";

interface CommentProps {
  username?: string;
  content: string;
  date: string;
  src: string;
  handleSection: (section: string)=> void;
  isReply?: boolean
}

const CommentItem = ({ username, content, date , src, handleSection, isReply}: CommentProps) => {


  return (
    <CommentItemStyled style={isReply ? {marginLeft: 35, marginTop: 20}: {}}>
      <Avatar src={src} style={{minWidth: 30, height: 30, display: "inline-block"}} />
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
          <span className="icon" onClick={ () => handleSection("text_area")}><CommentOutlined /> Reply</span>
          <span className="icon"><RetweetOutlined /> Share</span>
        </div>
        <div className="time-comment">
          <span>{formatDateString(date)}</span>
        </div>
       </div>
       
      </CommentInfoItem>
    </CommentItemStyled>
  );
};

export default CommentItem;
