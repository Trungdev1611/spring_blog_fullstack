import {Navigate} from 'react-router-dom'
import Layout1 from '../Layout/Layout1';

interface ChildrenProps {
    children: JSX.Element;
    childrenHeader?: JSX.Element
}
const PrivateRoute = ({children}:ChildrenProps ) => {

    const token = localStorage.getItem("token")
    if (!token) {
        return <Navigate to="/login" />;
      } else {
        return <Layout1 childrenBody={children}   />;
      }
 
}

export default PrivateRoute
