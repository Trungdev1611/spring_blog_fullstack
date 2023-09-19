import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Apiclient } from "../../apis/config";
import { ContainerFlexCenter, WidthContainer } from "../../styled/common";
import {
  AuthorStyled,
  DateStyledPost,
  HeadingPost,
} from "../../styled/styledPostItem";
import PostComment from "../../assets/Comments/PostComment";
import CommentItem from "../../assets/Comments/CommentItem";
import { PostDetailProps } from "../Types";
import AuthorPostDetail from "./AuthorPostDetail";

const PostDetail = () => {
  const params = useParams();
  const [postDetail, setPostDetail] = useState<PostDetailProps | null>(null);
  useEffect(() => {
    async function getPostDetail() {
      const res = await Apiclient.get(`/v1/posts/v2/${params.id}`);
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
            <AuthorStyled>{postDetail?.user?.fullName}&nbsp; </AuthorStyled>
            <DateStyledPost> /{postDetail?.dateCreated}/</DateStyledPost>
            <div className="text under-line" style={{ marginLeft: "-10px" }}>
              {postDetail?.countComment || 0} comments
            </div>
          </div>
          <HeadingPost>{postDetail?.heading}</HeadingPost>
          <div className="flex" style={{ gap: 100 }}>
            <div style={{ width: "70%", fontSize: 18 }}>
              <div>{postDetail?.content}</div>
            </div>

            <div style={{ width: "30%" }}>
              <AuthorPostDetail postDetail={postDetail} />
            </div>
          </div>

          <div style={{ marginTop: 20, marginBottom: 20 }}>
            <PostComment src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52qqyY2Mosgxt-Pt00pZy4TqIhCanFTwyLwC-D0z5&s" />
          </div>

          <div style={{ display: "flex", flexDirection: "column", gap: 20 }}>
            {postDetail?.comments?.map((item) => {
              return (
                <CommentItem
                  key={item.idComment}
                  username={item?.userComment}
                  content={item.contentComment}
                  date={item.dateComment}
                />
              );
            })}
          </div>
        </WidthContainer>
      </ContainerFlexCenter>
    </div>
  );
};

export default PostDetail;
