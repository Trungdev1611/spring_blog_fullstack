import { useParams } from 'react-router-dom'

const PostDetail = () => {
    const params = useParams()
    console.log("params", params)
  return (
    <div>
      PostDetail {params.id}
    </div>
  )
}

export default PostDetail
