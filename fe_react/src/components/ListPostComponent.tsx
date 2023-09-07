import { useEffect, useState } from "react";
import PostItem from "./PostItem";
import { styled } from "styled-components";
import { Apiclient } from "../apis/config";
import { PostItemProps } from "./Types";

const PostContainer = styled.div`
  display: flex;
  gap: 35px;
  flex-direction: column;
  max-width: 70vw;
  @media (max-width: 768px) {
    max-width: 90vw;
  }
`;

const ListPostComponent = () => {
  const [listPost, setListPost] = useState<PostItemProps[]>([]);
  useEffect(() => {
    async function getAllPost() {
      try {
        const res = await Apiclient.get(`/posts`);
        if (res.status) {
          console.log("resdata", res);
          setListPost(res.data);
        }
      } catch (error) {
        alert("Có lỗi xảy ra");
      }
    }
    getAllPost();
  }, []);
  return (
    <PostContainer>
      {listPost.map((item, index) => {
        return (
          <PostItem
            key={index}
            authorName={item.authorName}
            content={item.content.slice(0, 200)}
            dateCreated={item.dateCreated}
            id={item.id}
            avatar={item.avatar}
          />
        );
      })}
    </PostContainer>
  );
};

export default ListPostComponent;
