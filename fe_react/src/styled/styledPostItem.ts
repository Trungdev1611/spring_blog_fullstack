import { styled } from "styled-components";

export const PostItemContainer = styled.div`
  display: flex;
  gap: 20px;
  .post_info {
    width: 100%;
  }
  .post-content {
    display: flex;
    gap: 50px;
    width: 100%;
    @media (max-width: 768px) {
      flex-direction: column-reverse;
      gap: 20px;
    }

    .post_info {
      display: flex;
      flex-direction: column;
      gap: 15px;
     
    }
  }
`;

export const AuthorStyled = styled.div`
  color: #d33a2c;
  font-size: 18px;
  font-weight: 600;
  cursor: pointer;
`;
export const HeadingPost = styled.div`
  color: #333;
  transition: color 0.1s linear;
  font-size: 26px;
  max-width: 70%;
  font-weight: 600;
  &:hover {
    color: #d33a2c;
    cursor: pointer;
  }
`;

export const ContentPost = styled.div`
  line-height: 26px;
  flex: 1

`;

export const DateStyledPost = styled.div`
  color: #aaa;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  margin-right: 20px;
  text-transform: uppercase;
`;
export const DesctiotionPost = styled.span`
  color: #333;
`;

export const CommentWrapper = styled.div`
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
        cursor: pointer;
      }
    }
  }
`;
