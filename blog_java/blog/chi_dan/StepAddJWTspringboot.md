# Hỏi

Để code màn login và register trong springboot, khi login trả ra jwt, jwt có phân quyền, và khi trả ra jwt thì những request sau sẽ phải gửi kèm jwt trong request tôi đang định đi theo những bước sau
1. tạo model User
2. tạo model Roles
3. Tạo mối quan hệ giữa role và user là model user_roles @ManytoMany
4. tạo class JWtProvider để cung cấp các utils function như create jwt, verify jwt, 
5. Viết restController với login và register
6. Cấu hình url permitAll với url register và url login

các bước trên có đúng không, còn thiếu gì nữa không

# Trả lời
# Xây Dựng Hệ Thống Xác Thực và Phân Quyền Sử Dụng JWT trong Spring Boot

Dưới đây là một tóm tắt các bước cần thực hiện để xây dựng hệ thống xác thực và phân quyền sử dụng JSON Web Tokens (JWT) trong Spring Boot:

1. **Tạo Model User và Roles**: Tạo các model `User` và `Role` để đại diện cho người dùng và vai trò trong hệ thống.

2. **Tạo Mối Quan Hệ Giữa User và Role**: Thiết lập mối quan hệ nhiều-nhiều giữa `User` và `Role` thông qua một bảng trung gian như `UserRoles`.

3. **Tạo JWtProvider**: Tạo lớp `JwtProvider` để cung cấp các chức năng cần thiết như tạo JWT, xác minh JWT và trích xuất thông tin từ JWT.

4. **Viết RestController Cho Login và Register**: Viết REST API cho quá trình đăng nhập và đăng ký người dùng. Trong quá trình đăng nhập, tạo JWT và trả về cho người dùng.

5. **Cấu Hình URL PermitAll**: Cấu hình các URL như `register` và `login` để cho phép truy cập mà không cần xác thực.

6. **Thêm Cấu Hình Spring Security**: Thêm cấu hình Spring Security để quản lý quá trình xác thực và phân quyền. Sử dụng các annotations như `@EnableWebSecurity`, `@EnableGlobalMethodSecurity` và `@PreAuthorize`.

7. **Tạo Filter Cho Xác Thực Bằng JWT**: Tạo một filter để kiểm tra và xác thực JWT trong tiêu đề của các yêu cầu gửi từ client. Đặt thông tin xác thực trong `SecurityContextHolder`.

8. **Custom UserDetails**: Tạo một custom class implement `UserDetailsService` để cung cấp thông tin chi tiết về người dùng và tùy chỉnh cách thông tin này được truy vấn và sử dụng.

9. **Quản Lý Phân Quyền**: Xác định quyền của người dùng và sử dụng annotations như `@PreAuthorize` trên các phương thức hoặc lớp controller để kiểm soát quyền truy cập.

10. **Điều Khiển Quá Trình Đăng Ký và Đăng Nhập**: Đảm bảo kiểm tra các điều kiện hợp lệ trong quá trình đăng ký và đăng nhập, bao gồm kiểm tra sự tồn tại của người dùng, mật khẩu, xác nhận mật khẩu và các điều kiện khác.

11. **Quản Lý Lỗi và Thông Báo**: Xử lý các lỗi xảy ra trong quá trình xác thực và phân quyền, và cung cấp các thông báo lỗi thích hợp cho người dùng.

12. **Tạo Exception Handler**: Tạo xử lý exception handler để quản lý các lỗi và trả về các thông báo lỗi phù hợp cho client.

--------------------------------------------------------------------------

# Lớp UserDetails Service (UserDetails  mặc định là gì)

# UserDetails trong Spring Security

Trong Spring Security, `UserDetails` là một giao diện (interface) được sử dụng để cung cấp thông tin về người dùng trong quá trình xác thực và phân quyền. `UserDetails` đại diện cho thông tin cơ bản của một người dùng như tên đăng nhập, mật khẩu, vai trò và quyền hạn.

`UserDetails` chứa các thông tin cơ bản như sau:

- **Tài Khoản**: Tên đăng nhập của người dùng. Trong Spring Security, thông thường được gọi là "username" hoặc "email".

- **Mật Khẩu**: Mật khẩu của người dùng. Để bảo mật, mật khẩu thường được lưu trữ dưới dạng mã hóa hoặc hash.

- **Vai Trò (Roles)**: Các vai trò (roles) mà người dùng thuộc về. Ví dụ: "ROLE_USER", "ROLE_ADMIN",...

- **Quyền Hạn (Authorities)**: Các quyền hạn (authorities) cụ thể mà người dùng có. Quyền hạn là mức phân quyền chi tiết hơn so với vai trò.

- **Trạng Thái Kích Hoạt (Enabled)**: Trạng thái xác định xem tài khoản người dùng có được kích hoạt hay không.

- **Trạng Thái Không Bị Khóa (Account Non-Expired)**: Trạng thái xác định xem tài khoản người dùng có bị khóa hay không.

- **Mật Khẩu Không Hết Hạn (Credentials Non-Expired)**: Trạng thái xác định xem mật khẩu của người dùng có hết hạn hay không.

- **Trạng Thái Không Bị Khóa (Account Non-Locked)**: Trạng thái xác định xem tài khoản người dùng có bị khóa không.

`UserDetails` cho phép Spring Security truy cập và sử dụng thông tin về người dùng trong quá trình xác thực và phân quyền. Mặc định, Spring Security đi kèm với một lớp cài đặt `User` để triển khai giao diện `UserDetails`, nhưng bạn cũng có thể tạo custom `UserDetails` nếu cần tùy chỉnh hơn về cách thông tin người dùng được quản lý và xử lý.
