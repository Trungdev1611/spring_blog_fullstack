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
    font-weight: 600;
    font-size: 15px;
    color: #333;
    .content-comment {
         font-size: 14px;
         color: #697374;
         margin-left: 10px;
         font-weight: 400;
    }
  }
  .sub-content {
    display: flex;
    color: #aaa;
    justify-content: space-between;
    font-size: 13px;
    align-items: center;
  }
  .comment-action {
    display: flex;
    gap: 26px;
    color: #aaa;
    .icon {
      cursor: pointer;
    }
  }
`;
