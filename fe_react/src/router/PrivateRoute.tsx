import {Navigate} from 'react-router-dom'

interface ChildrenProps {
    children: JSX.Element;
}
const PrivateRoute = ({children}:ChildrenProps ) => {

    const token = localStorage.getItem("token")
    if (!token) {
        return <Navigate to="/login" />;
      } else {
        return children;
      }
 
}

export default PrivateRoute
