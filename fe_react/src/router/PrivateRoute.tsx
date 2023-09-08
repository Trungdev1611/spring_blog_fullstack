import {Navigate} from 'react-router-dom'
import { ListPostContainer } from '../styled/common';

interface ChildrenProps {
    children: JSX.Element;
}
const PrivateRoute = ({children}:ChildrenProps ) => {

    const token = localStorage.getItem("token")
    if (!token) {
        return <Navigate to="/login" />;
      } else {
        return <ListPostContainer>{children}</ListPostContainer> ;
      }
 
}

export default PrivateRoute
