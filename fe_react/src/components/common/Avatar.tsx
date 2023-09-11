import React, { CSSProperties } from 'react'
import { styled } from 'styled-components'

interface AvatarProps {
    linkAvatar: string,
    styles?: CSSProperties
}

const Avatarcontainer = styled.div`
    width: 80px;
    height: 80px;
    border: 3px solid red;
    transform: rotate(-20deg);
    border-radius: 15px;
    flex: 0 0 auto;
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
      border-radius: 10px;
    }
  
`
const Avatar = ({linkAvatar, styles}: AvatarProps) => {
  return (
    <Avatarcontainer  style={styles}>
    <img
      src={linkAvatar}
      alt="img1"
    />
  </Avatarcontainer>
  )
}


export default Avatar
