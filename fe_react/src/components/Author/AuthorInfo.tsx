import { styled } from "styled-components";
import Avatar from "../common/Avatar";

const AuthorInfoContainer = styled.div`
  background-color: rgb(211, 58, 44);
  color: white;
  padding-bottom: 25px;
  .info-wrapper {
    .info-general {
      display: flex;
      .author_name {
        margin-left: 30px;
        margin-bottom: 40px;
        div {
          font-size: 25px;
        }
        h3 {
          font-size: 45px;
          font-weight: 600;
        }
      }
    }
    .info-orther {
      display: flex;
      gap: 40px;
      @media (max-width: 992px) {
        flex-direction: column;
        gap: 20px;
      }
      .info-desc {
        line-height: 35px;
        font-size: 20px;
      }
      .info-social {
        h5 {
          margin-bottom: 20px;
          color: #333;
          min-width: 200px;
        }
        ul {
          @media (max-width: 992px) {
            display: flex;
            gap: 20px;
          }
          li {
            padding: 8px;
            text-decoration: underline;
            &:hover {
              color: #333;
            }
          }
        }
      }
    }
  }
`;

const AuthorInfo = () => {
  return (
    <AuthorInfoContainer>
      <div className="info-wrapper">
        <div className="info-general">
          <Avatar linkAvatar="https://files.smashing.media/authors/hannah-milan-200px.jpg" className="rotate"/>
          <div className="author_name">
            <div>AUTHOR</div>
            <h3>Hannah Milan</h3>
          </div>
        </div>

        <div className="info-orther">
          <p className="info-desc">
            Hannah is a product designer passionate about accessibility and
            inclusive design. She’s also the creator of A11yResources (later
            acquired by getstark.co). Hannah recently released a new
            accessibility resource A11y Bites where she curates bite-sized,
            digestible accessibility tips and tricks. She’s constantly on the
            lookout for new design and accessibility insights and knowledge to
            share with the community.
          </p>
          <div className="info-social">
            <h5>FIND HANNAH ELSEWHERE:</h5>
            <ul>
              <li>Twitter</li>
              <li>Website</li>
              <li>Instagram</li>
            </ul>
          </div>
        </div>
      </div>
    </AuthorInfoContainer>
  );
};

export default AuthorInfo;
