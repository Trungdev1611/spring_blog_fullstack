import { Avatar, Input } from "antd";
import { Button } from "antd";

const PostComment = ({
  src,
  lengthConv,
  handleCreateComment,
  comment,
  setComment
}: {
  src: string;
  lengthConv: number;
  handleCreateComment: (content:string) => void,
  comment: string,
  setComment: React.Dispatch<React.SetStateAction<string>>

}) => {


  return (
    <div>
      <h3>The Conversation ({lengthConv})</h3>
      <Input.TextArea
        rows={4}
        placeholder="Leave your comment"
        onChange={(e) => setComment(e.target.value)}
        style={{ marginTop: 10 }}
        id="text_area"
        value={comment}
      />
      <div
        style={{
          display: "flex",
          justifyContent: "flex-end",
          alignItems: "center",
          marginTop: 20,
          gap: 20,
        }}
      >
        {/* <span>{username}</span> */}
        <Avatar src={src} />
        <Button
          size="large"
          style={{
            backgroundColor: "#d33a2c",
            color: "white",
          }}
          onClick={() => handleCreateComment(comment)}
        >
          Post Comment
        </Button>
      </div>
    </div>
  );
};

export default PostComment;
