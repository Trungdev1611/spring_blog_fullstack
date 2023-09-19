import  { useEffect, useState} from "react";
import AuthorInfo from "./AuthorInfo";
import { ContainerFlexCenter, PostContainer, WidthContainer } from "../../styled/common";
import { Apiclient } from "../../apis/config";
import PostItem from "../PostItem";
import { useLocation} from "react-router-dom";


interface AuthorProps {
  email: string,
  fullName: string,
  id:number,
  posts: Posts[]
}
interface Posts {
  avatar: string,
  content: string,
  dateCreated:string,
  heading: string,
  idPost: number

}

const Author = () => {
  const [dataPostInUser, setDataPostInUser] = useState<AuthorProps|null>(null)
  const location = useLocation()

  useEffect(() => {
  async   function getListPostWithUser() {
      const res = await Apiclient.get(`/v1/user/${location.state}`)
      // if(res.status)
      console.log("res", res)
      setDataPostInUser(res?.data)
    }
    location.state && getListPostWithUser()
  }, [location.state])

  return (
    <div>
      <ContainerFlexCenter $isRed = {true} >
        <WidthContainer>
          <AuthorInfo />
        </WidthContainer>
      </ContainerFlexCenter>

      <ContainerFlexCenter $isRed={false}>
      <WidthContainer>
        <PostContainer>
          {dataPostInUser?.posts?.map((item, index) => {
            return (
              <PostItem
                key={index}
                fullNameUser={dataPostInUser.fullName}
                contentPost={item.content}
                dateCreated={item.dateCreated}
                idPost={item.idPost}
                profile_picture={item.avatar}
                email={dataPostInUser.email}
                headingPost={item.heading}
              />
            );
          })}
        </PostContainer>
      </WidthContainer>
    </ContainerFlexCenter>
    </div>
  );
};

export default Author;
