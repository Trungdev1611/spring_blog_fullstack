
import { styled } from "styled-components";
import { Input } from "antd";
import DropdownHeader from "./DropdownHeader";

interface LayoutProps1 {
  childrenBody: JSX.Element;
}

const HeaderContainer = styled.div`
  background-color: rgb(211, 58, 44);
  color: white;
  position: sticky;
  top: 0;
  z-index: 100;
`;

const NavBarContainer = styled.div`
  display: flex;
  min-height: 120px;
  align-items: center;
  row-gap: 20px;
  .logo {
    width: 60px;
    height: 80px;
    margin-right: 30px;
  }
  .list-nav {
    display: flex;
    justify-content: flex-start;
    flex: 1;
    list-style: none;
    row-gap: 10px;

    li {
      color: white;
      font-weight: 600;
      padding: 10px 15px;
      border-radius: 8px;
      font-size: 18px;
      font-family: "Lato", sans-serif;
      cursor: pointer;
      &.active {
        background-color: rgb(169, 46, 35);
      }
      &:hover {
        background-color: rgb(169, 46, 35);
      }
    }
  }
  .search-container {
    width: 25%;
    min-width: 250px;
    padding: 10px;
    max-width: 300px;
    .ant-btn-icon {
      color: rgb(211, 58, 44);
      font-weight: 600;
    }
    .ant-input-group-wrapper {
      width: 100%;
    }
  }
`;

const Layout1 = ({ childrenBody }: LayoutProps1) => {
  return (
    <div>
      <HeaderContainer>
        <NavBarContainer>
          <div className="logo">
            <img
              src="https://www.smashingmagazine.com/images/logo.svg"
              alt=""
            />
          </div>

          <ul className="list-nav">
            <li className="active">Articles</li>
            <li>Books</li>
            <li>WorkShops</li>
            <li>Conference</li>
            <li>MemberShip</li>
            <DropdownHeader />
          </ul>

          <div className="search-container">
            <Input.Search
              placeholder="Search articles..."
              onSearch={(value) => console.log(value)}
              size="large"
            />
          </div>
        </NavBarContainer>
      </HeaderContainer>
      {childrenBody}
    </div>
  );
};

export default Layout1;
