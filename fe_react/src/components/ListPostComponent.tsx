import { useEffect, useState } from "react";
import PostItem from "./PostItem";
import { styled } from "styled-components";
import { Apiclient } from "../apis/config";
import { PostItemProps } from "./Types";
import { ContainerFlexCenter, WidthContainer } from "../styled/common";

const PostContainer = styled.div`
  display: flex;
  gap: 35px;
  margin-top: 35px;
  flex-direction: column;
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
    <ContainerFlexCenter $isRed={false}>
      <WidthContainer>
        <PostContainer>
          {listPost.map((item, index) => {
            return (
              <PostItem
                key={index}
                fullNameUser={item.user.full_name}
                contentPost={item.content.slice(0, 200)}
                dateCreated={item.dateCreated}
                idPost={item.id}
                profile_picture={item.user.profile_picture}
                email={item.user.email}
                headingPost={item.heading}
              />
            );
          })}
        </PostContainer>
      </WidthContainer>
    </ContainerFlexCenter>
  );
};

export default ListPostComponent;
