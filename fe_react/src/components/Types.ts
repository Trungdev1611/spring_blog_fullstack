export interface PostItemProps {
    heading: string;
    avatar: string;
    content: string;
    dateCreated: null | string;
    authorName: string;
    authorId?: number;
    id: number;
    timeRead?:string
    counter_comments?:string
  }