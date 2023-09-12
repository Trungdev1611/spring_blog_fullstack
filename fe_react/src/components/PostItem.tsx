import { CalendarOutlined, CommentOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import { PropstypePost } from "./Types";
import { AuthorStyled, CommentWrapper, ContentPost, DateStyledPost, DesctiotionPost, HeadingPost, PostItemContainer } from "../styled/styledPostItem";
import Avatar from "./common/Avatar";

const PostItem = ({
  fullNameUser,
  contentPost,
  dateCreated,
  idPost,
  headingPost,
  profile_picture,
  email,timeRead, counter_comments, idUser
}: PropstypePost) => {
  const navigate = useNavigate();
  function navigateDetailPost() {
    idPost && navigate(`post_detail/${idPost}`,{state: idPost});
  }

  function navigateDetailAuthor() {
    fullNameUser && idUser && navigate(`author/${fullNameUser}`, {state: idUser});
  }
  return (
    <PostItemContainer>
    <Avatar linkAvatar= {profile_picture}/>
      <div className="post_info">
        <AuthorStyled onClick={navigateDetailAuthor}>
          {fullNameUser}
        </AuthorStyled>
        <HeadingPost onClick={navigateDetailPost}>
          {headingPost}
        </HeadingPost>
        <div className="post-content">
          <ContentPost>
            <DateStyledPost>{dateCreated || "Oct 16, 1994"}</DateStyledPost>
            <DesctiotionPost>{contentPost}</DesctiotionPost>
          </ContentPost>
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
