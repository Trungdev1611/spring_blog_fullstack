# ĐẶT VẤN ĐỀ: Sau khi đã nhận được jwt từ response login và validate jwt trong header gửi kèm rồi, thì bây giờ ta cần thực hiện việc dựa vào JWT đó để phân quyền truy cập resource

Giả sử: 
ADMIN có quyền truy cập toàn bộ tài nguyên resource, được quyền CRUD bất kỳ bài post nào
USER có quyền đọc tất cả các bài post, nhưng không thể xóa và delete các bài post không phải mình tạo

Hiện tại trong post của mình chưa có người tạo, nên chỉ cho phép ADMIN có quyền full, trong khi USER chỉ có quyền tạo

Các bước để phân quyền (sau khi có các điều kiện cần trong đặt vấn đề ở bên trên):

B1: **Bật annotation @EnableWebSecurity hoặc annotation @EnalbleMethodSecurity trong SpringSercurityConfiguration**
Annotation này là cần thiết cho việc phân quyền truy cập vào tài nguyên
-Phân biệt: 
   annotation  @EnableWebSecurity đi kèm với việc phân quyền ở cấp dộ url với requestMatcher, 
   ví dụ:   .antMatchers("/admin/**").hasRole("ADMIN")

   annotation  @EnalbleMethodSecurity đi kèm với việc phân quyền ở cấp phương thức với hasRole, hasAnyRole, hasAuthority. Vì là cấp phương thức, nên các trường hợp phức tạp hơn sẽ sử dụng với annotation này thay vì cấu hình trên url với @EnableWebSecurity

    _Khi sử dụng hasRole, hasAnyRole spring sẽ mặc định thêm prefix ROLE_ phía trước cho ta, nên ta chỉ cần làm VD:  
    @PreAuthorize("hasAnyRole('ADMIN')") , nhưng với hasAuthority ta phải làm là     @PreAuthorize("hasAuthority('ROLE_ADMIN'))"_

B2: **Đặt annotation @PreAuthorize("hasRole(TÊN_ROLE)")**
Đặt annotation @PreAuthorize("hasRole(TÊN_ROLE)") vào từng api trong controller để thực hiện phân quyền