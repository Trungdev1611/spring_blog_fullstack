# Đặt vấn đề: Khi gửi request lên, tôi muốn validate dữ liệu được gửi lên, vậy tôi phải sử dụng các annotation hỗ trợ

Dưới đây là một số annotation hỗ trợ validate trong Spring: (lưu ý ta phải cài thêm thư viện Hibernate Validator )

- `@NotNull`: Kiểm tra xem giá trị không được là null.
- `@NotBlank`: Kiểm tra xem giá trị không được là null hoặc không được chứa chỉ các khoảng trắng.
- `@NotEmpty`: Kiểm tra xem giá trị không được là null và không được rỗng (cho cả chuỗi và các collection).
- `@Size`: Kiểm tra xem kích thước của chuỗi hoặc collection nằm trong một phạm vi cho phép.
- `@Min` và `@Max`: Kiểm tra xem giá trị có lớn hơn hoặc bằng/giá trị nhỏ hơn hoặc bằng một giá trị min/max cho phép.
- `@Pattern`: Kiểm tra xem giá trị có khớp với một biểu thức chính quy.
- `@Email`: Kiểm tra xem giá trị có đúng định dạng của một địa chỉ email.
- `@Valid`: Được sử dụng để áp dụng validate lên một object phức tạp hoặc collection (ví dụ: validate các trường trong đối tượng).

**Bước 1**: Sử dụng các annotation trên trong DTO hoặc model hoặc entity
    _@Data
public class RegisterDTO {

    @NotBlank(message = "username không được để trống hoặc null")
    private String username;

    @NotBlank(message = "Password không được để trống hoặc null")
    private String password;

    @Email(message = "Email không đúng định dạng")
    private String email;

    @Size(max = 20, message = "Tên không được quá 20 ký tự")
    private String fullName;
}_


**Bước 2**: Sử dụng annotation @Valid trong endpoint cần validation

    _ @PostMapping("/create_post")
    public ResponseEntity<ResponseSuccess> createPost(@Valid @RequestBody PostDTO postDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("role user::: " + authentication.getAuthorities()); // in ra role user hiện tại
        ResponseSuccess reponse = new ResponseSuccess(postService.createPost(postDTO));
        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }_

**Bước 3**. bắt Exception để trả về client

_@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        String errors = "";
        // có nhiều lỗi trong trường mảng fieldErrors thì ta nối nó thành 1 chuỗi
        for (FieldError fieldError : fieldErrors) {
            errors = errors + ", " + (fieldError.getDefaultMessage());
        }
        String errorsSubString = errors.substring(2);

        ErrorResponse errorData = new ErrorResponse(errorsSubString, HttpStatus.BAD_REQUEST.value(), null);
        return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);
    }_