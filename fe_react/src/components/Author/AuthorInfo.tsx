import { styled } from "styled-components";
import Avatar from "../common/Avatar";

const AuthorInfoContainer = styled.div`
  background-color: rgb(211, 58, 44);
  color: white;
  .info-wrapper {
    .info-general {
        display: flex;
        .author_name {
            display: flex;
            row-gap: 20px;
        }
    }
    .info-orther {
        display: flex;
        gap: 20px;
        .info-desc {
            line-height: 22px;

        }
        .info-social {
            h5 {
                margin-bottom: 20px;

            }
            ul {
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
          <Avatar linkAvatar="https://files.smashing.media/authors/hannah-milan-200px.jpg" />
          <div className="author_name">
            <p>AUTHOR</p>
            <p>Hannah Milan</p>
          </div>
        </div>
        
        <div className="info-orther">
            <p className="info-desc">
            Hannah is a product designer passionate about accessibility and
          inclusive design. She’s also the creator of A11yResources (later
          acquired by getstark.co). Hannah recently released a new accessibility
          resource A11y Bites where she curates bite-sized, digestible
          accessibility tips and tricks. She’s constantly on the lookout for new
          design and accessibility insights and knowledge to share with the
          community.
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
