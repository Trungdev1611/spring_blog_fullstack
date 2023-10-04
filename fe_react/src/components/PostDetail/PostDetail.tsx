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
import {PostDetailProps } from "../Types";
import AuthorPostDetail from "./AuthorPostDetail";
import { showNotification } from "../../utils/Notifi";
import { Pagination, PaginationProps } from "antd";
import { dataCommentgroupItem, groupReplyIncomment } from "../../utils/fnhelper";

const PostDetail = () => {
  const params = useParams();
  const [postDetail, setPostDetail] = useState<PostDetailProps | null>(null);
  const [listComment, setListcomment] = useState<dataCommentgroupItem[] | []>([]);
  const [section, setSection] = useState<string | null>(null);
  const [callback, setCallback] = useState<number | null>(null);
  const [comment, setComment] = useState<string>("");
  const [flat, setFlat] = useState<number | null>(null);
  // const [currentPagePagi, setCurrentPagePagi] = useState(1);
  const [totalElement, setTotalElement] = useState(1)
  const [dataPage, setDataPage] = useState<{pageIndex: number, pageSize: number}>({
    pageIndex: 0,
    pageSize: 5
  })

  const [activeReplyIndex, setActiveReplyIndex] = useState<string>("") 
  useEffect(() => {
    async function getPostDetail() {
      const res = await Apiclient.get(`/v1/posts/v2/${params.id}`);
      setPostDetail(res.data);
    }
    params.id && getPostDetail();
  }, [params.id]);

  useEffect(() => {
    async function getListComment() {
      try {
        const res = await Apiclient.get(`/v1/comments/post/${params.id}`, dataPage);
        setListcomment(groupReplyIncomment(res.data.content));
        setTotalElement(res.data.totalElement)
      } catch (error) {
        console.log("err", error);
      }
    }
    params.id && getListComment();
  }, [params.id, flat, dataPage]);

  useEffect(() => {
    function moveSection(sectionId: string) {
      const sectionSelect = document.getElementById(sectionId);

      sectionSelect?.scrollIntoView({ behavior: "smooth" });
      if (sectionId === "text_area") {
        sectionSelect?.focus();
      }
    }
    callback && section && moveSection(section);

    return () => {
      // window.removeEventListener('scroll', moveSection);
    };
  }, [section, callback]);

  const handleSection = (section: string) => {
    setSection(section);
    setCallback(Math.random());
  };

  async function handleCreateComment() {
    if (!comment) {
      showNotification("error", "Comment không được để trống");
    }
    const res = await Apiclient.post(`/v1/comments/create/${params.id}`, {
      contentComment: comment,
    });
    if (res.status) {
      showNotification("success", "Create comment success");
      setComment("");
      setFlat(Math.random());
    }
  }

  const handleChangePagination: PaginationProps["onChange"] = (newPageIndex) => {
    setDataPage(prev => {
      const data = {...prev, pageIndex: newPageIndex - 1}
      return data
    })
   
  };

  const handlePageSizeChange: PaginationProps['onShowSizeChange'] = (current, newPageSize) => {
    setDataPage(prev => {
      const data = {...prev, pageSize: newPageSize}
      return data
    }
      
      );
  };

  console.log("listcomment", listComment)
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
            <div
              className="text under-line"
              style={{
                marginLeft: "-10px",
                color: "#aaa",
                fontFamily: "Trebuchet MS",
              }}
              onClick={() => handleSection("commens_section")}
            >
              {listComment?.length || 0} comments
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
          <h3>The Conversation ({listComment?.length})</h3>
            <PostComment
              handleCreateComment={handleCreateComment}
              comment={comment}
              setComment={setComment}
              src={postDetail?.avatar || ""}
              isReply = {false}
            />
          </div>

          <div
            style={{
              display: "flex",
              flexDirection: "column",
              gap: 20,
              marginBottom: 20,
            }}
            id="commens_section"
          >
            {listComment?.map((item) => {
               return (
                <div key={item.idComment}>
                  <CommentItem
                    username={item?.userComment}
                    content={item.contentComment}
                    date={item.dateComment}
                    src={item.userImg}
                    handleSection={handleSection}
                    idComment = {`parent_${item.idComment}`}
                    setActiveReplyIndex = {setActiveReplyIndex}
                    activeReplyIndex = {activeReplyIndex}
                  />
                  {/* Hiển thị các CommentItem bên trong replies */}
                  {item?.replies?.map((reply) => (
                    <CommentItem
                      key={reply.idReply}
                      username={reply.username || ""}
                      content={reply.contentReply}
                      date={reply.dateReply}
                      src={reply.userImg || ""}
                      handleSection={handleSection}
                      isReply
                      idComment = {`reply_${reply.idReply}`}
                      setActiveReplyIndex = {setActiveReplyIndex}
                      activeReplyIndex = {activeReplyIndex}
                    />
                  ))}
                </div>
              );
            })}
          </div>
          <div style={{ display: "flex", justifyContent: "flex-end" }}>
            <Pagination
              onChange={handleChangePagination}
              size="default"
              current={dataPage.pageIndex + 1}
              showSizeChanger
              responsive
              pageSize={dataPage.pageSize}
              total={totalElement}
              onShowSizeChange={handlePageSizeChange}
            />
          </div>
        </WidthContainer>
      </ContainerFlexCenter>
    </div>
  );
};

export default PostDetail;
