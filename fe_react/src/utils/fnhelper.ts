import moment from "moment";
import momentDurationFormatSetup from "moment-duration-format";
import { ICommentItem } from "./Types";
export function formatDateString(dateString: string) {
  // Tạo một đối tượng Date từ chuỗi ngày tháng
  const date = moment(dateString);
  momentDurationFormatSetup(moment);
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const duration: any = moment.duration(moment().diff(date));

  if (duration.asDays() >= 1) {
    return duration.format("d [days] ago", { trim: "both" });
  } else {
    return duration.format("h [hours] ago", { trim: "both" });
  }
}

interface Ireply {
  idReply: number,
  dateReply: string,
  contentReply: string,
  username?:string,
  userImg?:string
}

export function getReply(obj:ICommentItem): Ireply[] {
  if(obj.idReply) {
      return [{
          idReply: obj.idReply,
          dateReply: obj.dateReply,
          contentReply: obj.contentReply
      }]
  }
  return []
}


export interface dataCommentgroupItem extends ICommentItem {

  replies: Ireply[] | []
}


export function groupReplyIncomment(content: ICommentItem[]): dataCommentgroupItem[] {
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  const objData: any = {}
  const  result:dataCommentgroupItem[] = []
 content.forEach((item: ICommentItem )=> {
    let replies
    const key = `commentId${item.idComment}`
  
    if(objData[key]) {
            replies =[...objData[key],...getReply(item) ]
            
            const findIndex = result.findIndex(item1 => item1.idComment === item.idComment)

            result[findIndex] = {...item, replies: replies}
      
    }
    else {
       replies = getReply(item)

       result.push({...item, replies: replies} )
    }
    objData[key] = getReply(item)


   
})
return result;
}