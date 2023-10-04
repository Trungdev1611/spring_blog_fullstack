import { Avatar, Input } from "antd";
import { Button } from "antd";

interface IuserInfo {
  id: number;
  fullName: string;
  email: string;
  profilePicture: string;
} 

const PostComment = ({
  src,
  handleCreateComment,
  comment,
  setComment,
  isReply,
}: {
  src: string;
  handleCreateComment: (content: string) => void;
  comment: string;
  setComment: React.Dispatch<React.SetStateAction<string>>;
  isReply: boolean;
}) => {

  let userInfo: (IuserInfo | null)  = null;
  const userInfoString: string | null = localStorage.getItem("userInfo");
  if(userInfoString !== null ) {
    userInfo = JSON.parse(userInfoString)
  }

  return (
    <div>
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
        <Avatar src={userInfo?.profilePicture} />
        <Button
          size="large"
          style={{
            backgroundColor: "#d33a2c",
            color: "white",
          }}
          onClick={() => handleCreateComment(comment)}
        >
          {isReply ? "Send Reply" : "Send Comment"}
        </Button>
      </div>
    </div>
  );
};

export default PostComment;
