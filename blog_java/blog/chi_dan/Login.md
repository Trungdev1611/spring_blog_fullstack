# CÁC BƯỚC CHO LOGIN VỚI JWT + SPRING SECURITY TRONG SPRINGBOOT

0. **LUỒNG CƠ BẢN** requestlogin -> controller login -> service login -> tạo ra đối tượng usernamePasswordAuthenticationToken với 2 tham số là username và password trong request -> truyền đối tượng vào AuthenticationManager -> AuthenticationManager validate usernamePasswordAuthenticationToken với CustomUserDetailsService có chứa hàm loadUserByUserName(hàm này load data user từ cơ sở dữ liệu), sử dụng passwordEnCoder trong quá trình này. Nếu validated thành công thì tiếp tục đi tiếp để tạo ra token, nếu không ném ra exception

1. **TẠO LỚP USER (ENTITY)**: Lớp này chứa các thông tin USER
2. **TẠO LỚP ROLES (ENTITY)**: Lớp này chứa các thông tin về ROLE
3. **Thiết lập mối quan hệ giữa user và role**: Sẽ tạo ra 1 bảng mới user_role do mối quan hệ many-many
4. **Tạo lớp cấu hình SpingSercurity**
a/Lưu ý phải có passwordEncoder để decode, nếu không sẽ lỗi


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
7. **Tạo lớp CustomService** để implements UserDetailsService từ UserDetails của spring. Trong UserDetailsService sẽ có 1 method duy nhất là loadUserByUserName để load data từ database ra

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ghi đè phương thức loadUserByUsername của lớp UserDetailsService để load data
    // từ database
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("vao day login " + username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundEx());
        System.out.println("user " + user.getUsername() + user.getPassword());
        // lấy các quyền của user tìm được
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());

        System.out.println("11111" + user.toString());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

}

8. **AuthenticationManager.authenticated**: sẽ truyền usernamePasswordAuthenticationToken vào hàm này để spring tự động validate user ta đã truyền vào trong này với user được load từ database

9. **Validate Username không khớp**: Username không khớp sẽ được validate ở trong userDetailService  dòng này 

 User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundEx());

10. **Validate userName khớp nhưng mật khẩu không khớp**: Sẽ ném ra exception và ta sẽ bắt nó ở GlobalException

  @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tài khoản hoặc mật khẩu không đúng");
    }


