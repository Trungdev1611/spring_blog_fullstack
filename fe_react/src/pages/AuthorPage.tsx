import { useParams } from 'react-router-dom'
const AuthorPage = () => {
    const params = useParams()
    const author_name =params.author_name
  return (
    <div>
      AuthorPage {author_name} hiển thị các thông tin như 
      thông tin tóm tắt về người đó -resume
      Các list bài post theo id  của người đó
    </div>
  )
}

export default AuthorPage
