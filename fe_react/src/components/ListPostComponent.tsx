import { useEffect, useState } from "react";
import PostItem from "./PostItem";
import { styled } from "styled-components";
import { Apiclient } from "../apis/config";

const PostContainer = styled.div`
  display: flex;
  gap: 35px;
  flex-direction: column;
  max-width: 70vw;
  @media (max-width: 768px) {
    max-width: 90vw;
  }
`;
interface ListPostType {
  heading: string;
  avatar: string;
  content: string;
  dateCreated: null | string;
  authorName: string;
  authorId: number;
}
const ListPostComponent = () => {
  const [listPost, setListPost] = useState<ListPostType[]>([]);
  useEffect(() => {
    async function getAllPost() {
      try {
        const res = await Apiclient.get(`/posts`);
        if (res.status) {
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
            author={item.authorName}
            brief={item.content.slice(0, 100)}
            date={item.dateCreated}
          />
        );
      })}
      {/* <PostItem />
      <PostItem />
      <PostItem />
      <PostItem />
      <PostItem />
      <PostItem /> */}
    </PostContainer>
  );
};

export default ListPostComponent;
