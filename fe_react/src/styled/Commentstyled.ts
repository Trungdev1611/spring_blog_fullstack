import styled from "styled-components";

export const CommentItemStyled = styled.div`
  display: flex;
  gap: 20px;
`;

export const CommentInfoItem = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
  .username_comment {
    font-weight: 500;
    font-size: 16px;
    display: flex;
    column-gap: 100px;
    align-items: center;
    & span:last-child {
      font-weight: normal;
      font-size: 13px;
      color: #aaa;
    }
  }
  .content-comment {
    font-size: 14px;
    color: #333;
  }
  .comment-action {
    display: flex;
    gap: 10px;
    color: #aaa;
    font-size: 14px;
    margin-top: 5px;
    .icon {
    }
  }
`;
