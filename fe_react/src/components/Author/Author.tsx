import React from "react";
import AuthorInfo from "./AuthorInfo";
import { ContainerFlexCenter, WidthContainer } from "../../styled/common";

const Author = () => {
  return (
    <div>
      <ContainerFlexCenter $isRed = {true} >
        <WidthContainer>
          <AuthorInfo />
        </WidthContainer>
      </ContainerFlexCenter>
    </div>
  );
};

export default Author;
