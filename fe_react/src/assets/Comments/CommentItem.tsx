import { Avatar } from "antd";
import { CommentInfoItem, CommentItemStyled } from "../../styled/Commentstyled";
import { DislikeOutlined, LikeOutlined } from "@ant-design/icons";
import { formatDateString } from "../../utils/fnhelper";

interface CommentProps {
  username: string;
  content: string;
  date: string;
}

const CommentItem = ({ username, content, date }: CommentProps) => {
  return (
    <CommentItemStyled>
      <Avatar src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52qqyY2Mosgxt-Pt00pZy4TqIhCanFTwyLwC-D0z5&s" style={{minWidth: 40, height: 40, display: "inline-block"}} />
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
          <span className="reply">Reply</span>
          <span className="share">Share</span>
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
