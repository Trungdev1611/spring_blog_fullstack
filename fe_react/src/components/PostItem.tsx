import { CalendarOutlined, CommentOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import { PropstypePost } from "./Types";
import { CommentWrapper, PostItemContainer } from "../styled/styledPostItem";
import Avatar from "./common/Avatar";

const PostItem = ({
  fullNameUser,
  contentPost,
  dateCreated,
  idPost,
  headingPost,
  profile_picture,
  email,timeRead, counter_comments
}: PropstypePost) => {
  const navigate = useNavigate();
  function navigateDetailPost() {
    idPost && navigate(`post_detail/${idPost}`);
  }

  function navigateDetailAuthor() {
    fullNameUser && navigate(`author/${fullNameUser}`);
  }
  return (
    <PostItemContainer>
    <Avatar linkAvatar= {profile_picture} />
      <div className="post_info">
        <div className="author" onClick={navigateDetailAuthor}>
          {fullNameUser}
        </div>
        <div className="heading" onClick={navigateDetailPost}>
          {headingPost}
        </div>
        <div className="post-content">
          <div className="content">
            <span className="date">{dateCreated || "Oct 16, 1994"}</span>
            <span className="description">{contentPost}</span>
          </div>
          <CommentWrapper>
            <div className="info">
              <div className="icon">
                <CalendarOutlined />
              </div>
              <div className="text">{timeRead}</div>
            </div>

            <div className="info">
              <div className="icon">
                <CommentOutlined />
              </div>
              <div className="text under-line">{counter_comments}</div>
            </div>
          </CommentWrapper>
        </div>
      </div>
    </PostItemContainer>
  );
};

PostItem.defaultProps = {
  heading:
    "Designing Accessible Text Over Images: Best Practices, Techniques And Resources (Part 2)",
  author: "Hannah Milan",
  date: "august 4, 2023",
  brief: `In this two-part series of articles, Hannah Milan covers the best
    practices when using various accessible text over images
    techniques for designing your web and mobile app content.`,
  timeRead: `12 min read`,
  counter_comments: `2 comments`,
};
export default PostItem;
