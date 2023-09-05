# Features of the Blog Project

1. **User Authentication and Registration:** Utilize Spring Security and JWT to build a secure authentication and registration system. This allows users to log in to the system and create new accounts.

2. **User Management:** Implement a user management feature for admins, enabling them to modify user information, grant permissions, and delete users.

3. **Create, Edit, and Delete Posts:** Allow users to create new posts, edit existing ones, and delete posts on the blog. Implement the logic for this on the backend using Spring Boot.

4. **Post Categorization:** Enable users to categorize posts using tags or categories. Display posts in different categories on the user interface.

5. **Post Comments:** Add the capability to comment on posts. You can create a simple comment system or integrate external commenting services.

6. **Search Functionality:** Integrate a search function so that users can search for posts based on keywords.

7. **Post Ratings:** Allow users to rate posts or comments. This can encourage interaction and engagement from users.

8. **Social Sharing:** Add social sharing links so that users can share posts on various social media platforms.

9. **User Interface Management:** Utilize ReactJS to create a user-friendly and intuitive user interface. Integrate UI libraries like Material-UI or Ant Design to create a visually appealing and professional interface.

10. **Forgot Password Functionality:** Implement a "forgot password" feature that enables users to request a new password via email or phone number.

11. **Post and author:** @ManyToOne: 1 tác giả thì có nhiều post và 1 post thì chỉ có 1 tác giả (tên tác giả được xác định qua name và email)