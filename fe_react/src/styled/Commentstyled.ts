import styled from "styled-components";

export const CommentItemStyled = styled.div`
  display: flex;
  gap: 20px;
`;

export const CommentInfoItem = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
  width: 100%;
  .username_comment {
    font-weight: 500;
    font-size: 16px;
    .content-comment {
      font-size: 14px;
      color: #333;
    }
  }
  .sub-content {
    display: flex;
    color: #aaa;
    justify-content: space-between;
    font-size: 14px;
    align-items: center;
  }
  .comment-action {
    display: flex;
    gap: 20px;
    color: #aaa;
    .icon {
    }
  }
`;
