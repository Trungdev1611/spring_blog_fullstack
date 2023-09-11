import { useParams } from "react-router-dom";
import Author from "../components/Author/Author";
import { WidthContainer } from "../styled/common";
const AuthorPage = () => {
  const params = useParams();
  const author_name = params.author_name;
  return (
      <Author/>

  );
};

export default AuthorPage;
