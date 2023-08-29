# CÁC BƯỚC CHO LOGIN VỚI JWT + SPRING SECURITY TRONG SPRINGBOOT

1. **TẠO LỚP USER (ENTITY)**: Lớp này chứa các thông tin USER
2. **TẠO LỚP ROLES (ENTITY)**: Lớp này chứa các thông tin về ROLE
3. **Thiết lập mối quan hệ giữa user và role**: Sẽ tạo ra 1 bảng mới user_role do mối quan hệ many-many
4. **Tạo lớp cấu hình SpingSercurity**
a/Lưu ý phải có passwordEncoder để decode


@Configuration // annotation này để spring biết đây là file cấu hình
@EnableWebSecurity
public class SpringSecurityConfiguration {

    JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SpringSecurityConfiguration(JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()) // disable csrf bảo vệ

                // luồng auth cho truy cập mà không cần bảo vệ
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.GET, "/").permitAll()

                        .requestMatchers("/api/auth/**").permitAll()

                        .anyRequest().authenticated())
                // bắt exception ở đây
                .exceptionHandling((ex) -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))

                // disable session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // http.addFilterBefore(jwtAuthenticationFilter,
        // UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

5. **Tạo Controller cho login**
6. **Tạo lớp service cho login**: Trong đó 
a/UsernamePasswordAuthenticationToken: là 1 Object nhận 2 tham số của username và password được gửi lên từ request
b/ AuthenticationManager: có method authenticated chịu trách nhiệm cho việc xác thực. Nếu có lỗi xảy ra sẽ được 

@Service
public class AuthServiceImpl implements AuthService {
    public AuthenticationManager authenticationManager;
    public JwtProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
            JwtProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDTO loginDTO) {

        // set body từ request vào authenticcationManager
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword());

        System.out.println("aaa1" + usernamePasswordAuthenticationToken);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // đặt thông tin xác thực vào context bảo mật của Spring Security, để ứng dụng
        // biết rằng người dùng đã xác thực thành công.
        // Chẳng hạn, có thể sử dụng annotations như @Secured, @PreAuthorize,
        // @PostAuthorize để kiểm soát quyền truy cập vào các phương thức hoặc API trong
        // ứng dụng.
        System.out.println("aaaaaaaaaaa");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;

    }

}