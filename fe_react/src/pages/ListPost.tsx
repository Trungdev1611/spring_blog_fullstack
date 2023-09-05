import React from "react";
import ListPostComponent from "../components/ListPostComponent";
import { styled } from "styled-components";

const ListPostContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const ListPost = () => {
  return (
    <ListPostContainer>
      <ListPostComponent />
    </ListPostContainer>
  );
};

export default ListPost;
