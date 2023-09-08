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
  }