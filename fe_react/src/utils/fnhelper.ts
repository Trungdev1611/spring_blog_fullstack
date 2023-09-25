import moment from "moment";
import momentDurationFormatSetup from "moment-duration-format";
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
