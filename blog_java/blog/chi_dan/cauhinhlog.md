# Đặt vấn đề: Ta cần cấu hình log cho toàn bộ dự án vào file => mục đích: quản lý log dự án, lưu log

**B1: Thêm thư viện log**
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-logging -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-logging</artifactId>
		</dependency>

**B2: Thêm file log vào resource folder với tên là logback.xml**
<configuration>
    <appender name="rollingFile" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/myapp.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>logs/myapp.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
              <!-- max size của file log -->
            <maxFileSize>10MB</maxFileSize>

            <!-- số file lưu tối đa (sẽ chỉ giữ 7 file 10MB, file thứ 8 thì file đầu sẽ bị xóa) -->
            <maxHistory>7</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    <!-- INFO để chỉ rằng các cấp độ log cao hơn info sẽ được log (thứ tự cấp độ TRACE, DEBUG, INFO, WARN, ERROR.) -->
    <root level="INFO">
        <appender-ref ref="rollingFile"/>
    </root>
</configuration>
