import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Apiclient } from "../apis/config";
import { ContainerFlexCenter, WidthContainer } from "../styled/common";
import {
  AuthorStyled,
  DateStyledPost,
  HeadingPost,
} from "../styled/styledPostItem";
import Avatar from "./common/Avatar";
import { Link } from "react-router-dom";
import PostComment from "../assets/Comments/PostComment";
import CommentItem from "../assets/Comments/CommentItem";
import { PostDetailProps } from "./Types";

const PostDetail = () => {
  const params = useParams();
  const [postDetail, setPostDetail] = useState<PostDetailProps | null>(null);
  useEffect(() => {
    async function getPostDetail() {
      const res = await Apiclient.get(`/posts/${params.id}`);
      console.log("res", res);
      setPostDetail(res.data);
    }
    params.id && getPostDetail();
  }, [params.id]);
  return (
    <div>
      <ContainerFlexCenter $isRed={false}>
        <WidthContainer>
          <div
            className="flex"
            style={{ marginTop: 50, alignItems: "center", marginBottom: 20 }}
          >
            <AuthorStyled>{postDetail?.user?.full_name}</AuthorStyled>
            <DateStyledPost>/ {postDetail?.dateCreated} /</DateStyledPost>
            <div className="text under-line">2 comments</div>
          </div>
          <HeadingPost>{postDetail?.heading}</HeadingPost>
          <div className="flex" style={{ gap: 100 }}>
            <div style={{ width: "70%", fontSize: 18 }}>
            {postDetail?.content}
            </div>
            <div style={{ width: "30%" }}>
              <Avatar
                linkAvatar={postDetail?.avatar || ""}
                styles={{ transform: "rotate(0deg)", width: 100, height: 100 }}
              />
              <div>
                <h3>ABOUT THE AUTHOR</h3>
                {postDetail?.content}...
                <Link to={"/abc"}>More</Link>
              </div>
            </div>
          </div>

          <div style={{ marginTop: 20, marginBottom: 20 }}>
            <PostComment 
            username={postDetail?.user?.full_name || ""}
            src ="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52qqyY2Mosgxt-Pt00pZy4TqIhCanFTwyLwC-D0z5&s"/>
          </div>

          <div style={{ display: "flex", flexDirection: "column", gap: 20 }}>
            <CommentItem username = {postDetail?.user?.full_name} content ="No thank you, but at least is not as stupid as the wavy eyebrown trend"/>
            <CommentItem username = {postDetail?.user?.full_name} content ="No thank you, but at least is not as stupid as the wavy eyebrown trend"/>
            <CommentItem username = {postDetail?.user?.full_name} content ="No thank you, but at least is not as stupid as the wavy eyebrown trend"/>
            <CommentItem username = {postDetail?.user?.full_name} content ="No thank you, but at least is not as stupid as the wavy eyebrown trend"/>
            <CommentItem username = {postDetail?.user?.full_name} content ="No thank you, but at least is not as stupid as the wavy eyebrown trend"/>

          </div>
        </WidthContainer>
      </ContainerFlexCenter>
    </div>
  );
};

export default PostDetail;
