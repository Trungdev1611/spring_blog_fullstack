import { CalendarOutlined, CommentOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import { styled } from "styled-components";
import { PostItemProps } from "./Types";

const PostItemContainer = styled.div`
  display: flex;
  gap: 20px;
  .img-wrapper {
    width: 80px;
    height: 80px;
    border: 3px solid red;
    transform: rotate(-20deg);
    border-radius: 15px;
    flex: 0 0 auto;
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 10px;
    }
  }
  .post-content {
    display: flex;
    gap: 50px;
    @media (max-width: 768px) {
        flex-direction: column-reverse;
        gap: 20px
    }
  }
  .post_info {
    display: flex;
    flex-direction: column;
    gap: 15px;
    .author {
      color: #d33a2c;
      font-size: 18px;
      font-weight: 600;
    }
    .heading {
      color: #333;
      transition: color 0.1s linear;
      font-size: 26px;
      max-width: 70%;
      font-weight: 600;
      &:hover {
        color: #d33a2c;
        cursor: pointer;
      }
    }
    .content {
      line-height: 26px;
      .date {
        color: #aaa;
        font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
          "Lucida Sans", Arial, sans-serif;
        margin-right: 20px;
        text-transform: uppercase;
      }
      .description {
        color: #333;
      }
    }
  }
`;

const CommentWrapper = styled.div`
  line-height: 26px;
  min-width: 200px;
  .info {
    display: flex;
    gap: 10px;
    .icon {
      color: #d33a2c;
    }
    .text {
      color: #333;
      &.under-line {
        text-decoration: underline;
      }
    }
  }
`;

const PostItem = ({heading, authorName, content, dateCreated, timeRead, counter_comments, id }: PostItemProps) => {
  const navigate = useNavigate()
  function navigateDetailPost() {
   id &&  navigate(`post_detail/${id}`)
  }
  return (
    <PostItemContainer>
      <div className="img-wrapper">
        <img
          src="https://files.smashing.media/authors/hannah-milan-200px.jpg"
          alt="img1"
        />
      </div>

      <div className="post_info">
        <div className="author">{authorName}</div>
        <div className="heading" onClick={navigateDetailPost}>
         {heading}
        </div>
        <div className="post-content">
          <div className="content">
            <span className="date">{dateCreated|| "Oct 16, 1994"}</span>
            <span className="description">
             {content}
            </span>
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

PostItem.defaultProps =  {
    heading: "Designing Accessible Text Over Images: Best Practices, Techniques And Resources (Part 2)",
    author: "Hannah Milan",
    date: "august 4, 2023",
    brief: `In this two-part series of articles, Hannah Milan covers the best
    practices when using various accessible text over images
    techniques for designing your web and mobile app content.`,
    timeRead: `12 min read`,
    counter_comments: `2 comments`
}
export default PostItem;
