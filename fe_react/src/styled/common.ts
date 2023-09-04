import { styled } from "styled-components";

interface RegisterProps {
  isLogin?: boolean;
}
export const RegisterContainer = styled.div<RegisterProps>`
  display: flex;
  gap: 30px;
  margin-top: 40px;
  justify-content: center;
  align-items: center;
  flex-direction: ${props => props.isLogin ? 'column': "horizontal"};
  @media (max-width: 600px) {
    flex-direction: column;
    }
  .form-register {
    display: flex;
    flex-direction: column;
    max-width: 400px;
    min-width: 300px;
    width: 100%;
  

  }
  .heading {

  }
`;

export const Heading = styled.div`
  text-align: center;
  font-size: 50px;
  margin-top: 40px;
`;
export const TextTitle = styled.div`
  font-size: 16px;
  text-align: center;
  padding: 20px 0;
  & span {
    text-decoration: underline;
  }
`;
export const LoginWrapper = styled.div`
  padding: 20px 0;
  flex: 1;
  flex-direction: column;
  display: flex;
  gap: 10px;
  justify-content: center;
  .description {
    font-size: 14px;
    max-width: 400px;
    margin-top: 20px;
    line-height: 22px;
  }
`;
export const TypeLogin = styled.div`
  display: flex;
  gap: 10px;
  align-items: center;
  .icon {
    border: 1px solid #ccc;
    border-radius: 50%;
    padding: 8px;
  }
  .text {
    color: grey;
  }
`;
