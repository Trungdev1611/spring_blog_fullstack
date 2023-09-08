import { styled } from "styled-components";

export const PostItemContainer = styled.div`
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
      gap: 20px;
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
      }
    }
  }
`;