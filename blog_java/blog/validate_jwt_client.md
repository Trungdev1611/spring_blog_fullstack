# ĐẶT VẤN ĐỀ: Trước đó khi người dùng đăng nhập (nếu đúng tài khoản ) sẽ trả ra jwt ở response. Người dùng cũng có thể đăng ký tài khoản mới

# Nhiệm vụ hiện tại là validate token đã gửi về trước đó khi client gửi kèm trong header Bearer token, nếu hợp lệ cho phép truy cập resource, nếu sai trả về lỗi tương ứng

B1: **Tạo 1 bộ lọc JwtTokenFilter**: Bộ lọc này sẽ extends OncePerRequestFilter (cái này mỗi request đều đi qua nó). Ở trong này ta sẽ ghi đè hàm doFilterInternal

\`\`\`java
   public class JWTAuthenticationFilter extends OncePerRequestFilter { 
     @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {}
   }
\`\`\`

B2: **Lấy token từ request và sau đó validate nó**: token thường được gửi kèm header trong authorization Bearer. Ta trích xuất nó sau đó verify với hàm verify của mình
     String token = getTokenFromRequest(request);

B3. **Validate xong lấy được data từ token**: khi validate token xong và thành công, ta lấy được username bên trong token, ta sẽ load được user từ username với hàm loadUserByUsername trong lớp UserDetailsService đã triển khai từ UserDetailsService trước đó
     String username = jwtTokenProvider.getUsername(token);
     UserDetails userDetails = userDetailsService.loadUserByUsername(username);

B4. **Set user tìm được trong database vào UsernamePasswordAuthenticationToken** Khi lấy được user từ database, ta set user đó vào lớp UsernamePasswordAuthenticationToken để tạo ra đối tượng 
\`\`\`java
UsernamePasswordAuthenticationToken
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
\`\`\`

B5. **Set vào SecurityContextHolder** Đặt đối tượng UsernamePasswordAuthenticationToken đã lấy được ở B4 vào SecurityContextHolder (context của spring security). Khi đặt vào đây, người dùng của ta có thể truy cập được tài nguyên

B6. **Cho phép spring security tiếp tục thực hiện các request tiếp theo**
  filterChain.doFilter(request, response);

B7. trong file cấu hình spring security, ta add thêm bộ lọc vừa tạo ở bước 1 JwtTokenFilter vào nó
\`\`\`java
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
    //các cấu hình requestmatcher, permitAll, hasRole trước đó ở đây

    //Add bộ lọc vừa tạo vào với dòng sau
      http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
}
\`\`\`

.B8. **Xong** Đến đây là xong. Kiểm tra và test
