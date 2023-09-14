import React from 'react'
import { Link } from 'react-router-dom'
import Avatar from '../common/Avatar'
import { PostDetailProps } from '../Types'

interface AuthorpostDetailProps {
    postDetail: PostDetailProps| null
}


const AuthorPostDetail = ({postDetail}: AuthorpostDetailProps) => {
  return (
    <div>
      <Avatar
                linkAvatar={postDetail?.user?.picture || ""}
                styles={{ transform: "rotate(0deg)", width: 100, height: 100, marginBottom: 10 }}
              />
              <div>
                <h3>ABOUT THE AUTHOR</h3>
                <h5 className='mt10 italic-font'>Email: {postDetail?.user?.email}</h5>
                Hannah is a product designer passionate about accessibility and inclusive design. She’s also the creator of A11yResources (later acquired by)
                <Link to={"/abc"}>More</Link>
              </div>
    </div>
  )
}

export default AuthorPostDetail
