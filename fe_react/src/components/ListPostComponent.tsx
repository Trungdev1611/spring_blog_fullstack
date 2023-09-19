import { useEffect, useState } from "react";
import PostItem from "./PostItem";
import { Apiclient } from "../apis/config";
import { PostItemProps } from "./Types";
import { ContainerFlexCenter,  PostContainer,  WidthContainer } from "../styled/common";


const ListPostComponent = () => {
  const [listPost, setListPost] = useState<PostItemProps[]>([]);
  useEffect(() => {
    async function getAllPost() {
      try {
        const res = await Apiclient.get(`/v1/posts`);
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
                idUser = {item.user.id}
              />
            );
          })}
        </PostContainer>
      </WidthContainer>
    </ContainerFlexCenter>
  );
};

export default ListPostComponent;
