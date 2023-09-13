import { Avatar } from 'antd'
import { CommentInfoItem, CommentItemStyled } from '../../styled/Commentstyled'

const CommentItem = ({username, content}: {username?:string, content:string}) => {
  return (
    <CommentItemStyled>
        <Avatar src ="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS52qqyY2Mosgxt-Pt00pZy4TqIhCanFTwyLwC-D0z5&s"/>
        <CommentInfoItem> 
            <div className="username_comment">
              {username}
            </div>
            <div className="content-comment">
              {content}
            </div>
            <div className='comment-action'>
                <span className='reply'>Reply</span>
                <span className='share'>Share</span>
                <span className='likes'>1 Like</span>
                <span className='icon'>like_icon</span>
                <span className='icon'>unlike_icon</span>
            </div>
        </CommentInfoItem>
    </CommentItemStyled>
  )
}

export default CommentItem
