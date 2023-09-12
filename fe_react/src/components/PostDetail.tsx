import { useEffect } from "react";
import { useParams } from "react-router-dom";
import { Apiclient } from "../apis/config";
import { ContainerFlexCenter, WidthContainer } from "../styled/common";
import {
  AuthorStyled,
  DateStyledPost,
  DesctiotionPost,
  HeadingPost,
} from "../styled/styledPostItem";
import Avatar from "./common/Avatar";

const PostDetail = () => {
  const params = useParams();
  useEffect(() => {
    async function getPostDetail() {
      const res = await Apiclient.get(`/posts/${params.id}`);
      console.log("res", res);
    }
    params.id && getPostDetail();
  }, [params.id]);
  return (
    <div>
      <ContainerFlexCenter $isRed={false}>
        <WidthContainer>
          <div className="flex" style={{marginTop: 50}}>
            <AuthorStyled>Hannah Milan</AuthorStyled>
            <DateStyledPost>/ AUG 11, 2023 /</DateStyledPost>
            <div className="text under-line">2 comments</div>
          </div>
          <HeadingPost>
            Designing Accessible Text Over Images: Best Practices, Techniques
            And Resources (Part 2)
          </HeadingPost>
          <div className="flex" style={{ gap: 100 }}>
            <div style={{ width: "70%", fontSize: 18 }}>
              What is the text over images design pattern? How do we apply this
              pattern to our designs without sacrificing legibility and
              readability? The text over images design pattern is a design
              technique used to place text on top of images. It is often used to
              provide information about the image or to serve as the main
              website navigation. However, this technique can quickly sacrifice
              legibility and readability if there is not enough contrast between
              the text and the image. To prevent this, designers need to ensure
              that the text and the image have a high enough contrast ratio to
              be legible and readable. Additionally, designers should also make
              sure the text is positioned in the right place, away from any
              image elements that might cause confusion, distraction, or make it
              difficult to read. eadability? The text over images design pattern
              is a design technique used to place text on top of images. It is
              often used to provide information about the image or to serve as
              the main website navigation. However, this technique can quickly
              sacrifice legibility and readability if there is not enough
              contrast between the text and the image. To prevent this,
              designers need to ensure that the text and the image have a high
              enough contrast ratio to be legible and readable. Additionally,
              designers should also make sure the text is positioned in the
              right place, away from any image elements that might cause
              confusion, distraction, or make it difficult to read. eadability?
              The text over images design pattern is a design technique used to
              place text on top of images. It is often used to provide
              information about the image or to serve as the main website
              navigation. However, this technique can quickly sacrifice
              legibility and readability if there is not enough contrast between
              the text and the image. To prevent this, designers need to ensure
              that the text and the image have a high enough contrast ratio to
              be legible and readable. Additionally, designers should also make
              sure the text is positioned in the right place, away from any
              image elements that might cause confusion, distraction, or make it
              difficult to read.
            </div>
            <div style={{width: "30%"}}>
              <Avatar
                linkAvatar="https://files.smashing.media/authors/hannah-milan-200px.jpg"
                styles={{ transform: "rotate(0deg)", width: 100, height: 100 }}
              />
              <div>
                What is the text over images design pattern? How do we apply
                this pattern to our designs without sacrificing legibility and
                readability? The text over images design pattern is a design
                technique used to place text on top of images. It is often used
                to provide information about the image or to serve as the main
                website navigation. However, this technique can quic
              </div>
            </div>
          </div>
        </WidthContainer>
      </ContainerFlexCenter>
    </div>
  );
};

export default PostDetail;
