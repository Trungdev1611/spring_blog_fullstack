export interface PostItemProps {
    heading: string;
    avatar: string;
    content: string;
    dateCreated: null | string;
    id: number;
    timeRead?:string
    counter_comments?:string
    user: {
      full_name: string;
      email: string;
      profile_picture: string
      id: number
    }
  }

  export interface PropstypePost {
    fullNameUser: string;
    contentPost: string;
    dateCreated: string | null;
    idPost: number;
    profile_picture: string;
    email: string;
    headingPost: string;
  
    timeRead?: string,
    counter_comments?:string
    idUser?: number
  }

  export interface Comment {
    idComment: number,
    dateComment: string,
    contentComment: string,
    userComment: string
  }
  export interface PostDetailProps {
    avatar:string,
    content: string,
    id: number,
    heading: string,
    dateCreated: string,
    countComment: number,
    comments:Array<Comment>,
    user: {
        email:string,
        fullName: string,
        id:number,
        picture: string
    }
  }
  