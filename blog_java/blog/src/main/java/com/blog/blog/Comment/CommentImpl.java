package com.blog.blog.Comment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.blog.blog.Response.Paginate;
import com.blog.blog.auth.User;
import com.blog.blog.auth.UserRepository;
import com.blog.blog.exception.ResourceNotFoundEx;
import com.blog.blog.exception.ResourceNotFoundExWithMsg;
import com.blog.blog.post.Post;
import com.blog.blog.post.PostRepository;

@Service
public class CommentImpl implements CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;
    UserRepository userRepository;

    CommentImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentDTO createComment(Comment comment, Long idPost) {
        if (idPost == null) {
            throw new ResourceNotFoundEx();
        }
        Post post = postRepository.findById(idPost).orElseThrow(() -> new ResourceNotFoundEx());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsername(auth.getName()).orElseThrow(() -> new ResourceNotFoundEx());

        comment.setPost(post);
        comment.setUser(user);
        Comment commmentCreated = commentRepository.save(comment);
        return convertCommentToDTO(commmentCreated);
    }

    private CommentDTO convertCommentToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContentComment(comment.getContentComment());
        commentDTO.setUserId(comment.getUser().getId());

        return commentDTO;
    }

    public Paginate<List<CommentProjectionPost>> getCommentByIdPost(Long idPost, int pageIndex, int pageSize) {
        if (idPost == null) {
            throw new ResourceNotFoundEx("idPost not accept null");
        }
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<CommentProjectionPost> data = commentRepository.getListCommentByIdPost(idPost, pageable);

        // get pageIndex(số trang), pageSize(elements in page), totalElement and
        // listComment
        List<CommentProjectionPost> ListData = data.getContent();

        int pageCurrent = data.getNumber(); // lấy page hiện tại

        int pageSizeCurrent = data.getSize(); // lấy pageSize hiện tại

        // Long totalElement = data.getTotalElements(); // lấy tổng số phần tử

        Long totalElement = commentRepository.totalCountCommentByPostId(idPost); // lấy tổng số phần tử

        return new Paginate<List<CommentProjectionPost>>(pageCurrent, pageSizeCurrent, totalElement, ListData);

    }

    public List<Test> getCommentByIdPostTest(Long idPost, int pageIndex, int pageSize) {
        if (idPost == null) {
            throw new ResourceNotFoundEx("idPost not accept null");
        }

        return commentRepository.getListCommentByIdPostTest(idPost);

    }
}
