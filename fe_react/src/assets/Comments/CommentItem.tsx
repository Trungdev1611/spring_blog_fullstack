import { Avatar } from "antd";
import { CommentInfoItem, CommentItemStyled } from "../../styled/Commentstyled";
import { DislikeOutlined, LikeOutlined } from "@ant-design/icons";

interface CommentProps {
  username: string;
  content: string;
  date: string;
}

const CommentItem = ({ username, content, date }: CommentProps) => {
  return (
    <CommentItemStyled>
      <Avatar src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52qqyY2Mosgxt-Pt00pZy4TqIhCanFTwyLwC-D0z5&s" />
      <CommentInfoItem>
        <div className="username_comment">
          <span>{username || "user1"} </span>
          <span>{date}</span>
        </div>
        <div className="content-comment">{content}</div>
        <div className="comment-action">
          <span className="reply">Reply</span>
          <span className="share">Share</span>
          <span className="likes">1 Like</span>
          <span className="icon"><LikeOutlined /></span>
          <span className="icon"><DislikeOutlined /></span>
        </div>
      </CommentInfoItem>
    </CommentItemStyled>
  );
};

export default CommentItem;
